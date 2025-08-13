package com.otix.mobile.feature.trip.presentation.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TripDetailRoot(
    viewModel: TripDetailViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(event = Lifecycle.Event.ON_CREATE) {
        with(viewModel) {
            onIntent(intent = TripDetailContract.Intent.Init)
            onIntent(intent = TripDetailContract.Intent.LoadTrip)
        }
    }

    TripDetailScreen(uiState = uiState)
}