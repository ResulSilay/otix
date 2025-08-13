package com.otix.mobile.feature.trip.presentation.screen.trip

import com.otix.core.architecture.UiContract
import com.otix.mobile.feature.trip.presentation.screen.trip.TripContract.Intent.LoadTrip
import com.otix.mobile.feature.trip.presentation.screen.trip.TripContract.Intent.LoadTripMock

internal fun TripViewModel.reduceEvent(
    intent: UiContract.Intent,
) {
    when (intent) {
        is LoadTrip -> loadTripList(vehicleId = intent.vehicleId)
        is LoadTripMock -> loadTripMock()
    }
}