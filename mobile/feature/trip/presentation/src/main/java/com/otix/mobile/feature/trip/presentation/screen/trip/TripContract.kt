package com.otix.mobile.feature.trip.presentation.screen.trip

import com.otix.core.architecture.UiContract
import com.otix.shared.domain.model.Trip
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

object TripContract {

    data class State(
        val tripList: PersistentList<Trip> = persistentListOf(),
        val isLoading: Boolean = true
    ) : UiContract.State

    sealed class Intent : UiContract.Intent {

        data class LoadTrip(val vehicleId: String) : Intent()

        data object LoadTripMock : Intent()
    }

    sealed class Effect : UiContract.Effect
}
