package com.otix.mobile.feature.trip.presentation.screen.map

import com.otix.core.architecture.UiContract
import com.otix.mobile.feature.trip.presentation.screen.map.TripMapContract.Intent.LoadRoutes

internal fun TripMapViewModel.reduceEvent(
    intent: UiContract.Intent,
) {
    when (intent) {
        is LoadRoutes -> loadRoutes()
    }
}