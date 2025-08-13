package com.otix.shared.presentation.di

import com.otix.shared.presentation.shared.SharedViewModel
import org.koin.dsl.module

val sharedModule = module {

    single {
        SharedViewModel(
            getVehicle = get(),
            getProperty = get(),
            saveVehicle = get(),
            saveTrip = get(),
            tripManager = get(),
            locationManager = get()
        )
    }
}