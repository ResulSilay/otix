package com.otix.mobile.feature.settings.presentation.di

import com.otix.mobile.feature.settings.presentation.screen.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {

    viewModel {
        SettingsViewModel(
            getVehicles = get(),
            removeVehicle = get()
        )
    }
}