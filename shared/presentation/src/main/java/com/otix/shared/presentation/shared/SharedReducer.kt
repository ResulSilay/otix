package com.otix.shared.presentation.shared

import com.otix.core.architecture.UiContract
import com.otix.shared.presentation.shared.SharedContract.Intent.GetVehicle
import com.otix.shared.presentation.shared.SharedContract.Intent.Init
import com.otix.shared.presentation.shared.SharedContract.Intent.LaunchDemoAccount
import com.otix.shared.presentation.shared.SharedContract.Intent.SetGpsLocation
import com.otix.shared.presentation.shared.SharedContract.Intent.SetTrip
import com.otix.shared.presentation.shared.SharedContract.Intent.SetVehicleInfo

internal fun SharedViewModel.reduceEvent(
    intent: UiContract.Intent
) {
    when (intent) {
        Init -> {
            saveVehicle()
            getVehicle()
            getVehicleProperties()
        }

        SetTrip -> setTrip()

        LaunchDemoAccount -> launchDemoAccount()

        is SetVehicleInfo -> setCarInfo(vehicleInfo = intent.vehicleInfo)

        is SetGpsLocation -> setGpsLocation(location = intent.location)

        is GetVehicle -> {
            getVehicle(vehicleId = intent.id)
            getVehicleProperties(vehicleId = intent.id)
        }
    }
}