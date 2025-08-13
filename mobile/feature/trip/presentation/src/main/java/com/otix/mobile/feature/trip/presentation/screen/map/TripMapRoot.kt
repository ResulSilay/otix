package com.otix.mobile.feature.trip.presentation.screen.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TripMapRoot(
    viewModel: TripMapViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(event = Lifecycle.Event.ON_CREATE) {
        with(viewModel) {
            onIntent(intent = TripMapContract.Intent.LoadRoutes)
        }
    }

    TripMapScreen(uiState = uiState)
}