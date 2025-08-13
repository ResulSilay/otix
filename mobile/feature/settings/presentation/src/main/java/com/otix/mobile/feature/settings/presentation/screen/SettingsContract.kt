package com.otix.mobile.feature.settings.presentation.screen

import com.otix.core.architecture.UiContract
import com.otix.shared.domain.model.Vehicle
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

object SettingsContract {

    data class State(
        val vehicles: PersistentList<Vehicle> = persistentListOf()
    ) : UiContract.State

    sealed class Intent : UiContract.Intent {

        data object LoadVehicles : Intent()

        data class RemoveVehicle(val vehicleId: String) : Intent()
    }

    sealed class Effect : UiContract.Effect
}
