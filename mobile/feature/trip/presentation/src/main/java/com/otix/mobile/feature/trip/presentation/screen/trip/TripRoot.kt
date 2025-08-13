package com.otix.mobile.feature.trip.presentation.screen.trip

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun TripRoot(
    viewModel: TripViewModel = koinViewModel(),
    lazyListState: LazyListState,
    vehicleId: String,
    isDemoAccount: Boolean,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(event = Lifecycle.Event.ON_CREATE) {
        with(viewModel) {
            if (isDemoAccount) {
                onIntent(intent = TripContract.Intent.LoadTripMock)
            } else {
                onIntent(intent = TripContract.Intent.LoadTrip(vehicleId = vehicleId))
            }
        }
    }

    TripScreen(
        uiState = uiState,
        lazyListState = lazyListState,
        isDemoAccount = isDemoAccount
    )
}