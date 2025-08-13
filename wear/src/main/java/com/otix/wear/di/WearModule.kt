package com.otix.wear.di

import com.otix.wear.presentation.MainViewModel
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val wearModule = module {

    viewModel {
        MainViewModel(getLastCar = get())
    }

    single<Json> {
        Json { ignoreUnknownKeys = true }
    }
}