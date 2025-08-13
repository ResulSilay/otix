package com.otix.mobile.feature.fuel.presentation.screen.capacity

import androidx.lifecycle.viewModelScope
import com.otix.core.architecture.BaseViewModel
import com.otix.core.navigation.route.FuelCapacity
import com.otix.shared.domain.local.key.PropertyKey
import com.otix.shared.domain.local.usecase.GetPropertyUseCase
import com.otix.shared.domain.local.usecase.SavePropertyUseCase
import com.otix.shared.domain.model.VehicleProperty
import com.otix.shared.domain.resource.onSuccess
import com.otix.shared.presentation.shared.DEFAULT_BATTERY_CAPACITY
import com.otix.shared.presentation.shared.DEFAULT_FUEL_VOLUME
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class FuelCapacityViewModel(
    private val args: FuelCapacity,
    private val getProperty: GetPropertyUseCase,
    private val saveProperty: SavePropertyUseCase,
) : BaseViewModel<FuelCapacityContract.State, FuelCapacityContract.Intent, FuelCapacityContract.Effect>(
    initialState = FuelCapacityContract.State()
) {

    override suspend fun reduce(intent: FuelCapacityContract.Intent) = reduceEvent(intent = intent)

    internal fun getCapacityProperties() {
        viewModelScope.launch {
            getProperty(
                vehicleId = args.vehicleId,
                key = PropertyKey.FuelVolume.value
            ).collect { resource ->
                resource.onSuccess { result ->
                    setState {
                        copy(fuelVolume = result?.value?.toIntOrNull() ?: DEFAULT_FUEL_VOLUME)
                    }
                }
            }

            getProperty(
                vehicleId = args.vehicleId,
                key = PropertyKey.BatteryCapacity.value
            ).collect { resource ->
                resource.onSuccess { result ->
                    setState {
                        copy(batteryCapacity = result?.value?.toIntOrNull() ?: DEFAULT_BATTERY_CAPACITY)
                    }
                }
            }
        }
    }

    internal fun saveCapacities(fuelVolume: Int, batteryCapacity: Int) {
        viewModelScope.launch {
            val results = listOf(
                async {
                    saveProperty(
                        vehicleId = args.vehicleId,
                        key = PropertyKey.FuelVolume,
                        value = fuelVolume.toString()
                    )
                },
                async {
                    saveProperty(
                        vehicleId = args.vehicleId,
                        key = PropertyKey.BatteryCapacity,
                        value = batteryCapacity.toString()
                    )
                }
            ).awaitAll()

            if (results.all { it }) {
                sendEffect { FuelCapacityContract.Effect.Dismiss }
            }
        }
    }

    private suspend fun saveProperty(
        vehicleId: String,
        key: PropertyKey,
        value: String,
    ): Boolean = saveProperty(
        property = VehicleProperty(
            vehicleId = vehicleId,
            key = key.value,
            value = value
        )
    )
}
