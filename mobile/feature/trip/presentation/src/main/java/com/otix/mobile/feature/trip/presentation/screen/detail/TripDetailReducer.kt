package com.otix.mobile.feature.trip.presentation.screen.detail

import com.otix.core.architecture.UiContract
import com.otix.mobile.feature.trip.presentation.screen.detail.TripDetailContract.Intent.Init
import com.otix.mobile.feature.trip.presentation.screen.detail.TripDetailContract.Intent.LoadTrip

internal fun TripDetailViewModel.reduceEvent(
    intent: UiContract.Intent,
) {
    when (intent) {
        is Init -> init()
        is LoadTrip -> loadTrip()
    }
}