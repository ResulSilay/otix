package com.otix.wear.presentation

import com.otix.core.architecture.UiContract
import com.otix.shared.domain.model.VehicleInfo

object MainContract {

    data class State(
        val vehicleInfo: VehicleInfo = VehicleInfo()
    ) : UiContract.State

    sealed class Intent : UiContract.Intent {

        data object GetCar : Intent()
    }

    sealed class Effect : UiContract.Effect
}
