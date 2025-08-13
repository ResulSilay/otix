package com.otix.shared.presentation.shared

import android.location.Location
import com.otix.core.architecture.UiContract
import com.otix.shared.domain.model.PhoneInfo
import com.otix.shared.domain.model.VehicleInfo

object SharedContract {

    data class State(
        val phoneInfo: PhoneInfo = PhoneInfo(),
        val vehicleInfo: VehicleInfo = VehicleInfo(),
        val fuelVolume: Int = DEFAULT_FUEL_VOLUME,
        val batteryCapacity: Int = DEFAULT_BATTERY_CAPACITY,
        val isDemoAccount: Boolean = false
    ) : UiContract.State

    sealed class Intent : UiContract.Intent {

        data object Init : Intent()

        data object SetTrip : Intent()

        data object LaunchDemoAccount : Intent()

        data class SetVehicleInfo(val vehicleInfo: VehicleInfo) : Intent()

        data class SetGpsLocation(val location: Location?) : Intent()

        data class GetVehicle(val id: String) : Intent()
    }

    sealed class Effect : UiContract.Effect
}
