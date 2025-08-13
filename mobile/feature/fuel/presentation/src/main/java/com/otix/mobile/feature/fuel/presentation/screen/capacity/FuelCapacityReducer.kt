package com.otix.mobile.feature.fuel.presentation.screen.capacity

import com.otix.core.architecture.UiContract
import com.otix.mobile.feature.fuel.presentation.screen.capacity.FuelCapacityContract.Intent.LoadCapacity
import com.otix.mobile.feature.fuel.presentation.screen.capacity.FuelCapacityContract.Intent.SaveCapacity

internal fun FuelCapacityViewModel.reduceEvent(
    intent: UiContract.Intent
) {
    when (intent) {
        is LoadCapacity -> getCapacityProperties()

        is SaveCapacity -> saveCapacities(
            fuelVolume = intent.fuelVolume,
            batteryCapacity = intent.batteryCapacity
        )
    }
}