package com.otix.mobile.feature.settings.presentation.screen

import com.otix.core.architecture.UiContract
import com.otix.mobile.feature.settings.presentation.screen.SettingsContract.Intent.LoadVehicles
import com.otix.mobile.feature.settings.presentation.screen.SettingsContract.Intent.RemoveVehicle

internal fun SettingsViewModel.reduceEvent(
    intent: UiContract.Intent,
) {
    when (intent) {
        is LoadVehicles -> loadVehicles()

        is RemoveVehicle -> removeAtVehicle(vehicleId = intent.vehicleId)
    }
}