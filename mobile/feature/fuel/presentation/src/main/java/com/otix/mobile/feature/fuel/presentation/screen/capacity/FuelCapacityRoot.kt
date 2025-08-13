package com.otix.mobile.feature.fuel.presentation.screen.capacity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.otix.core.ui.provider.LocalNavigator

@Composable
fun FuelCapacityRoot(
    viewModel: FuelCapacityViewModel
) {
    val navigator = LocalNavigator.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(event = Lifecycle.Event.ON_CREATE) {
        viewModel.onIntent(intent = FuelCapacityContract.Intent.LoadCapacity)
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                FuelCapacityContract.Effect.Dismiss -> navigator.onBack()
            }
        }
    }

    FuelCapacitySheet(
        uiState = uiState,
        onSave = { fuelVolume, batteryCapacity ->
            viewModel.onIntent(
                intent = FuelCapacityContract.Intent.SaveCapacity(
                    fuelVolume = fuelVolume,
                    batteryCapacity = batteryCapacity
                )
            )
        }
    )
}