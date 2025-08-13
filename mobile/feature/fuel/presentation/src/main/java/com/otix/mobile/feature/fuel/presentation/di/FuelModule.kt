package com.otix.mobile.feature.fuel.presentation.di

import com.otix.core.navigation.route.FuelCapacity
import com.otix.mobile.feature.fuel.presentation.screen.capacity.FuelCapacityViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val fuelModule = module {

    viewModel { (navKey: FuelCapacity) ->
        FuelCapacityViewModel(
            args = navKey,
            getProperty = get(),
            saveProperty = get()
        )
    }
}