package com.otix.shared.presentation.shared

import android.location.Address
import android.location.Location
import androidx.lifecycle.viewModelScope
import com.otix.core.architecture.BaseViewModel
import com.otix.core.extension.getAddress
import com.otix.core.extension.getTime
import com.otix.core.extension.isNullOrNegative
import com.otix.core.extension.orZero
import com.otix.core.extension.toUUID
import com.otix.core.location.manager.LocationManager
import com.otix.shared.domain.local.key.PropertyKey
import com.otix.shared.domain.local.usecase.GetPropertyUseCase
import com.otix.shared.domain.local.usecase.GetVehicleUseCase
import com.otix.shared.domain.local.usecase.SaveTripUseCase
import com.otix.shared.domain.local.usecase.SaveVehicleUseCase
import com.otix.shared.domain.manager.TripManager
import com.otix.shared.domain.model.BatteryPercent
import com.otix.shared.domain.model.FuelPercent
import com.otix.shared.domain.model.GeoLocation
import com.otix.shared.domain.model.PhoneInfo
import com.otix.shared.domain.model.Trip
import com.otix.shared.domain.model.TripInfo
import com.otix.shared.domain.model.Vehicle
import com.otix.shared.domain.model.VehicleInfo
import com.otix.shared.domain.resource.onSuccess
import com.otix.shared.domain.type.VehicleState
import com.otix.shared.presentation.mock.getPhoneInfoMock
import com.otix.shared.presentation.mock.getVehicleInfoMock
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val DEFAULT_VEHICLE_NAME = "VEHICLE"
const val DEFAULT_FUEL_VOLUME = 50
const val DEFAULT_BATTERY_CAPACITY = 45

class SharedViewModel(
    private val getVehicle: GetVehicleUseCase,
    private val saveVehicle: SaveVehicleUseCase,
    private val getProperty: GetPropertyUseCase,
    private val saveTrip: SaveTripUseCase,
    private val tripManager: TripManager,
    private val locationManager: LocationManager,
) : BaseViewModel<SharedContract.State, SharedContract.Intent, SharedContract.Effect>(
    initialState = SharedContract.State()
) {
    private val routePoints = mutableListOf<GeoLocation>()
    private val fuelPercentages = mutableListOf<FuelPercent>()
    private val batteryPercentages = mutableListOf<BatteryPercent>()

    private val vehicleId: String
        get() = uiState.value.vehicleInfo.vehicleId.ifBlank {
            uiState.value.vehicleInfo.model.name.value.orEmpty().ifBlank { DEFAULT_VEHICLE_NAME }.toUUID()
        }

    private val currentLocation: Address?
        get() = locationManager.getAddressFromLocation(
            location = Location("CurrentLocationProvider").apply {
                latitude = uiState.value.phoneInfo.location?.latitude.orZero()
                longitude = uiState.value.phoneInfo.location?.longitude.orZero()
            }
        )?.firstOrNull()

    override suspend fun reduce(intent: SharedContract.Intent) = reduceEvent(intent = intent)

    internal fun launchDemoAccount() {
        setState {
            copy(
                vehicleInfo = getVehicleInfoMock(),
                phoneInfo = getPhoneInfoMock(),
                isDemoAccount = true
            )
        }
    }

    internal fun setCarInfo(vehicleInfo: VehicleInfo) {
        if (uiState.value.isDemoAccount) return

        setState {
            copy(vehicleInfo = vehicleInfo)
        }

        uiState.value.phoneInfo.location?.run {
            addRoutePoint(
                location = GeoLocation(
                    address = currentLocation.getAddress(),
                    latitude = latitude,
                    longitude = longitude,
                    timestamp = time
                )
            )
        }

        uiState.value.vehicleInfo.energyLevel.fuelPercent.value?.run {
            if (this.isNullOrNegative()) return

            addFuelPercent(
                fuelPercent = FuelPercent(
                    value = this,
                    timestamp = getTime()
                )
            )
        }

        uiState.value.vehicleInfo.energyLevel.batteryPercent.value?.run {
            if (this.isNullOrNegative()) return

            addBatterPercent(
                batteryPercent = BatteryPercent(
                    value = this,
                    timestamp = getTime()
                )
            )
        }

        saveVehicle()
    }

    private fun setPhoneInfo(phoneInfo: PhoneInfo) {
        if (uiState.value.isDemoAccount) return

        setState {
            copy(phoneInfo = phoneInfo)
        }

        saveVehicle()
    }

    internal fun setTrip() {
        if (uiState.value.isDemoAccount) return

        when (uiState.value.vehicleInfo.state) {
            VehicleState.DRIVING, VehicleState.PARKED -> saveTrip()
            VehicleState.OFF -> endTrip()
            else -> Unit
        }
    }

    internal fun setGpsLocation(location: Location?) {
        if (uiState.value.isDemoAccount) return

        setPhoneInfo(
            phoneInfo = uiState.value.phoneInfo.copy(
                location = location
            )
        )
    }

    internal fun getVehicle(vehicleId: String? = null) {
        if (uiState.value.isDemoAccount) return

        viewModelScope.launch {
            getVehicle(id = vehicleId ?: this@SharedViewModel.vehicleId).collectLatest { resource ->
                resource.onSuccess { result ->
                    setState {
                        copy(
                            vehicleInfo = result.info.copy(vehicleId = result.id)
                        )
                    }
                }
            }
        }
    }

    internal fun saveVehicle() {
        if (uiState.value.isDemoAccount) return

        viewModelScope.launch {
            saveVehicle(
                vehicle = Vehicle(
                    id = vehicleId,
                    info = uiState.value.vehicleInfo
                )
            )
        }
    }

    internal fun getVehicleProperties(vehicleId: String = this@SharedViewModel.vehicleId) {
        viewModelScope.launch {
            getProperty(
                vehicleId = vehicleId,
                key = PropertyKey.FuelVolume.value
            ).collect { resource ->
                resource.onSuccess { result ->
                    setState {
                        copy(fuelVolume = result?.value?.toIntOrNull().orZero())
                    }
                }
            }

            getProperty(
                vehicleId = vehicleId,
                key = PropertyKey.BatteryCapacity.value
            ).collect { resource ->
                resource.onSuccess { result ->
                    setState {
                        copy(fuelVolume = result?.value?.toIntOrNull().orZero())
                    }
                }
            }
        }
    }

    private fun saveTrip() {
        if (uiState.value.isDemoAccount) return

        val trip = Trip(
            vehicleId = uiState.value.vehicleInfo.vehicleId,
            info = TripInfo(
                currentLocation = GeoLocation(
                    address = currentLocation.getAddress(),
                    latitude = uiState.value.phoneInfo.location?.latitude.orZero(),
                    longitude = uiState.value.phoneInfo.location?.longitude.orZero(),
                    timestamp = getTime()
                ),
                startTime = getTime(),
                endTime = getTime(),
                startFuelPercent = uiState.value.vehicleInfo.energyLevel.fuelPercent.value.orZero(),
                endFuelPercent = uiState.value.vehicleInfo.energyLevel.fuelPercent.value.orZero(),
                fuelVolumeLiters = uiState.value.fuelVolume,
                fuelPercentages = fuelPercentages,
                startBatteryPercent = uiState.value.vehicleInfo.energyLevel.batteryPercent.value.orZero(),
                endBatteryPercent = uiState.value.vehicleInfo.energyLevel.batteryPercent.value.orZero(),
                batteryCapacityKWh = uiState.value.batteryCapacity,
                batteryPercentages = batteryPercentages,
                routePoints = routePoints
            )
        )

        viewModelScope.launch {
            val finishedTrip = tripManager.handleIncomingTrip(newTrip = trip)

            finishedTrip?.let {
                saveTrip(trip = trip)
                clear()
            }
        }
    }

    private fun endTrip() {
        if (uiState.value.isDemoAccount) return

        viewModelScope.launch {
            tripManager.getCurrentTrip()?.run {
                saveTrip(trip = this)
                clear()
                tripManager.reset()
            }
        }
    }

    private fun addRoutePoint(location: GeoLocation) {
        routePoints.add(location)
    }

    private fun addFuelPercent(fuelPercent: FuelPercent) {
        fuelPercentages.add(fuelPercent)
    }

    private fun addBatterPercent(batteryPercent: BatteryPercent) {
        batteryPercentages.add(batteryPercent)
    }

    private fun clear() {
        routePoints.clear()
        fuelPercentages.clear()
        batteryPercentages.clear()
    }
}
