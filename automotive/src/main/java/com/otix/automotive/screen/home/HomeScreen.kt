package com.otix.automotive.screen.home

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.hardware.CarHardwareManager
import androidx.car.app.model.Action.APP_ICON
import androidx.car.app.model.CarIcon
import androidx.car.app.model.Tab
import androidx.car.app.model.TabContents
import androidx.car.app.model.TabTemplate
import androidx.car.app.model.Template
import androidx.core.graphics.drawable.IconCompat
import androidx.lifecycle.lifecycleScope
import com.otix.automotive.R
import com.otix.automotive.manager.AccelerometerManager
import com.otix.automotive.manager.CompassManager
import com.otix.automotive.manager.EnergyLevelManager
import com.otix.automotive.manager.EnergyProfileFetcher
import com.otix.automotive.manager.EvStatusManager
import com.otix.automotive.manager.GyroscopeManager
import com.otix.automotive.manager.HardwareLocationManager
import com.otix.automotive.manager.MileageManager
import com.otix.automotive.manager.ModelFetcher
import com.otix.automotive.manager.SpeedManager
import com.otix.automotive.manager.TollCardManager
import com.otix.automotive.manager.VehicleInfoManager
import com.otix.automotive.manager.VehicleStateManager
import com.otix.automotive.screen.home.tab.TabInfo
import com.otix.automotive.screen.home.tab.Tabs
import com.otix.automotive.screen.home.template.getFuelTemplate
import com.otix.automotive.screen.home.template.getInfoTemplate
import com.otix.automotive.screen.home.template.getLocationTemplate
import com.otix.automotive.screen.home.template.getSensorTemplate
import com.otix.automotive.sender.PhoneWearVehicleInfoSender
import com.otix.core.extension.hasLocationPermission
import com.otix.core.resources.alias.StrArray
import com.otix.core.resources.strings.Strings
import com.otix.shared.domain.model.VehicleInfo
import com.otix.shared.presentation.shared.SharedContract
import com.otix.shared.presentation.shared.SharedViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class HomeScreen(carContext: CarContext) : Screen(carContext), KoinComponent {

    private val viewModel: SharedViewModel by inject()
    private val strings: Strings by inject()
    private val carHardwareManager = carContext.getCarService(CarHardwareManager::class.java)
    private val phoneWearVehicleInfoSender = PhoneWearVehicleInfoSender(context = carContext)
    private val mainExecutor = carContext.mainExecutor
    private var activeContentId: String = Tabs.INFO.id
    private val tabs = strings.getArray(id = StrArray.automotive_tabs)
    private var invalidateJob: Job? = null

    private val modelFetcher = ModelFetcher(manager = carHardwareManager, executor = mainExecutor)
    private val energyLevelManager = EnergyLevelManager(manager = carHardwareManager, executor = mainExecutor)
    private val energyProfileFetcher = EnergyProfileFetcher(manager = carHardwareManager, executor = mainExecutor)
    private val speedManager = SpeedManager(manager = carHardwareManager, executor = mainExecutor)
    private val mileageManager = MileageManager(manager = carHardwareManager, executor = mainExecutor)
    private val tollCardManager = TollCardManager(manager = carHardwareManager, executor = mainExecutor)
    private val evStatusManager = EvStatusManager(manager = carHardwareManager, executor = mainExecutor)
    private val accelerometerManager = AccelerometerManager(manager = carHardwareManager, executor = mainExecutor)
    private val gyroscopeManager = GyroscopeManager(manager = carHardwareManager, executor = mainExecutor)
    private val compassManager = CompassManager(manager = carHardwareManager, executor = mainExecutor)
    private val hardwareLocationManager = HardwareLocationManager(manager = carHardwareManager, executor = mainExecutor)
    private val vehicleStateManager = VehicleStateManager(scope = MainScope())

    private val hasLocationPermission: Boolean
        get() = carContext.hasLocationPermission()

    init {
        destroy()
        init()
    }

    private fun init() {
        runCatching {
            initManagerListener()
        }.onFailure { error ->
            error.printStackTrace()
        }
    }

    private fun destroy() {
        invalidateJob?.cancel()
        vehicleStateManager.stopPeriodicCheck()
        removeManagerListener()
    }

    private fun safeInvalidate() {
        invalidateJob?.cancel()
        invalidateJob = lifecycleScope.launch {
            delay(timeMillis = 100)
            invalidate()
        }
    }

    private fun initManagerListener() {
        modelFetcher.fetchModel { model ->
            viewModel.onIntent(
                intent = SharedContract.Intent.SetVehicleInfo(
                    vehicleInfo = viewModel.uiState.value.vehicleInfo.copy(
                        model = model
                    )
                )
            )

            safeInvalidate()
        }

        energyProfileFetcher.fetchEnergyProfile { energyProfile ->
            viewModel.onIntent(
                intent = SharedContract.Intent.SetVehicleInfo(
                    vehicleInfo = viewModel.uiState.value.vehicleInfo.copy(
                        energyProfile = energyProfile
                    )
                )
            )

            safeInvalidate()
        }

        registerCarInfoListener(manager = energyLevelManager) { energyLevel ->
            copy(energyLevel = energyLevel)
        }

        registerCarInfoListener(manager = speedManager) { speed ->
            copy(speed = speed)
        }

        registerCarInfoListener(manager = mileageManager) { mileage ->
            copy(mileage = mileage)
        }

        registerCarInfoListener(manager = tollCardManager) { tollCard ->
            copy(tollCard = tollCard)
        }

        registerCarInfoListener(manager = evStatusManager) { evStatus ->
            copy(evStatus = evStatus)
        }

        if (hasLocationPermission) {
            registerCarInfoListener(manager = accelerometerManager) { accelerometer ->
                copy(accelerometer = accelerometer)
            }

            registerCarInfoListener(manager = gyroscopeManager) { gyroscope ->
                copy(gyroscope = gyroscope)
            }

            registerCarInfoListener(manager = compassManager) { compass ->
                copy(compass = compass)
            }

            registerCarInfoListener(manager = hardwareLocationManager) { hardwareLocation ->
                copy(hardwareLocation = hardwareLocation)
            }
        }

        vehicleStateManager.startPeriodicCheck(
            getSpeed = {
                viewModel.uiState.value.vehicleInfo.speed.displaySpeedMetersPerSecond.value
            },
            stateCallback = { state ->
                with(viewModel) {
                    viewModel.onIntent(
                        intent = SharedContract.Intent.SetVehicleInfo(
                            vehicleInfo = uiState.value.vehicleInfo.copy(
                                state = state
                            )
                        )
                    )
                    viewModel.onIntent(intent = SharedContract.Intent.SetTrip)
                }

                safeInvalidate()
            }
        )
    }

    private fun removeManagerListener() {
        unRegisterCarInfoListener(manager = energyLevelManager)
        unRegisterCarInfoListener(manager = speedManager)
        unRegisterCarInfoListener(manager = mileageManager)
        unRegisterCarInfoListener(manager = tollCardManager)
        unRegisterCarInfoListener(manager = evStatusManager)
        unRegisterCarInfoListener(manager = accelerometerManager)
        unRegisterCarInfoListener(manager = gyroscopeManager)
        unRegisterCarInfoListener(manager = hardwareLocationManager)
    }

    private fun <T> registerCarInfoListener(
        manager: VehicleInfoManager<T>,
        update: VehicleInfo.(T) -> VehicleInfo,
    ) {
        manager.registerListener { value ->
            viewModel.onIntent(
                intent = SharedContract.Intent.SetVehicleInfo(
                    vehicleInfo = viewModel.uiState.value.vehicleInfo.update(value)
                )
            )

            phoneWearVehicleInfoSender.sendCarDataToWear(
                vehicleInfo = viewModel.uiState.value.vehicleInfo
            )

            safeInvalidate()
        }
    }

    private fun <T> unRegisterCarInfoListener(manager: VehicleInfoManager<T>) {
        runCatching {
            manager.unregisterListener()
        }.onFailure { error ->
            error.printStackTrace()
        }
    }

    private fun getTabs(): List<TabInfo> = listOf(
        TabInfo(
            id = Tabs.INFO.id,
            title = tabs[Tabs.INFO.position],
            iconRes = R.drawable.ic_vehicle
        ),
        TabInfo(
            id = Tabs.FUEL.id,
            title = tabs[Tabs.FUEL.position],
            iconRes = R.drawable.ic_fuel
        ),
        TabInfo(
            id = Tabs.SENSOR.id,
            title = tabs[Tabs.SENSOR.position],
            iconRes = R.drawable.ic_sensor
        ),
        TabInfo(
            id = Tabs.LOCATION.id,
            title = tabs[Tabs.LOCATION.position],
            iconRes = R.drawable.ic_location
        )
    )

    private fun CarContext.getTab(tabInfo: TabInfo) = Tab.Builder()
        .setTitle(tabInfo.title)
        .setIcon(
            CarIcon
                .Builder(IconCompat.createWithResource(this@CarContext, tabInfo.iconRes))
                .build()
        )
        .setContentId(tabInfo.id)
        .build()

    private fun CarContext.getTabContents(): TabContents {
        val vehicleInfo = viewModel.uiState.value.vehicleInfo
        val phoneInfo = viewModel.uiState.value.phoneInfo

        val template = when (Tabs.parse(id = activeContentId)) {
            Tabs.INFO -> {
                getInfoTemplate(strings = strings, vehicleInfo = vehicleInfo)
            }

            Tabs.FUEL -> {
                getFuelTemplate(strings = strings, vehicleInfo = vehicleInfo)
            }

            Tabs.SENSOR -> {
                getSensorTemplate(strings = strings, vehicleInfo = vehicleInfo)
            }

            Tabs.LOCATION -> {
                getLocationTemplate(
                    strings = strings,
                    vehicleInfo = vehicleInfo,
                    phoneInfo = phoneInfo
                )
            }
        }

        return TabContents.Builder(template).build()
    }

    override fun onGetTemplate(): Template {
        val tabCallback = object : TabTemplate.TabCallback {
            override fun onTabSelected(tabContentId: String) {
                activeContentId = tabContentId
                invalidate()
            }
        }

        val tabTemplate = TabTemplate.Builder(tabCallback)
            .setHeaderAction(APP_ICON)
            .apply {
                with(carContext) {
                    getTabs().forEach { tabInfo ->
                        addTab(getTab(tabInfo = tabInfo))
                    }
                    setTabContents(getTabContents())
                }
            }

        return tabTemplate
            .setHeaderAction(APP_ICON)
            .setActiveTabContentId(activeContentId)
            .build()
    }
}
