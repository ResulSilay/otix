package com.otix.mobile.feature.fuel.presentation.screen.capacity

import com.otix.core.architecture.UiContract
import com.otix.shared.presentation.shared.DEFAULT_BATTERY_CAPACITY
import com.otix.shared.presentation.shared.DEFAULT_FUEL_VOLUME

object FuelCapacityContract {

    data class State(
        val fuelVolume: Int = DEFAULT_FUEL_VOLUME,
        val batteryCapacity: Int = DEFAULT_BATTERY_CAPACITY,
        val isLoading: Boolean = true
    ) : UiContract.State

    sealed class Intent : UiContract.Intent {

        data object LoadCapacity : Intent()

        data class SaveCapacity(
            val fuelVolume: Int,
            val batteryCapacity: Int,
        ) : Intent()
    }

    sealed class Effect : UiContract.Effect {

        data object Dismiss : Effect()
    }
}
