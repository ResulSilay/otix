package com.otix.mobile.feature.trip.presentation.di

import com.otix.core.navigation.route.TripDetail
import com.otix.core.navigation.route.TripMap
import com.otix.mobile.feature.trip.presentation.screen.detail.TripDetailViewModel
import com.otix.mobile.feature.trip.presentation.screen.map.TripMapViewModel
import com.otix.mobile.feature.trip.presentation.screen.trip.TripViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val tripModule = module {

    viewModel {
        TripViewModel(getTripList = get())
    }

    viewModel { (navKey: TripDetail) ->
        TripDetailViewModel(args = navKey, getTrip = get())
    }

    viewModel { (navKey: TripMap) ->
        TripMapViewModel(args = navKey, getTrip = get())
    }
}