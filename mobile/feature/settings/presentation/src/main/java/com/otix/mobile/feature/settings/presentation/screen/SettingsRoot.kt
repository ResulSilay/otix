package com.otix.mobile.feature.settings.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsRoot(
    viewModel: SettingsViewModel = koinViewModel(),
    settingsController: SettingsController,
    selectedVehicleId: String,
    isDemoAccount: Boolean,
    onVehicleSelect: (vehicleId: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(event = Lifecycle.Event.ON_CREATE) {
        viewModel.onIntent(intent = SettingsContract.Intent.LoadVehicles)
    }

    SettingsScreen(
        uiState = uiState,
        settingsController = settingsController,
        isDemoAccount = isDemoAccount,
        selectedVehicleId = selectedVehicleId,
        onVehicleSelect = onVehicleSelect,
        onVehicleRemove = { vehicleId ->
            viewModel.onIntent(
                intent = SettingsContract.Intent.RemoveVehicle(vehicleId = vehicleId)
            )
        }
    )
}