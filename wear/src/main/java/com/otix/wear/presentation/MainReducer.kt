package com.otix.wear.presentation

import com.otix.core.architecture.UiContract
import com.otix.wear.presentation.MainContract.Intent.GetCar

internal fun MainViewModel.reduceEvent(
    intent: UiContract.Intent,
) {
    when (intent) {
        is GetCar -> getCar()
    }
}