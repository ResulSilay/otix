package com.otix.mobile.feature.trip.presentation.screen.detail

import com.otix.core.architecture.UiContract
import com.otix.shared.domain.model.Trip

object TripDetailContract {

    data class State(
        val tripId: Long = -1,
        val isDemoAccount: Boolean = false,
        val trip: Trip? = null,
        val isLoading: Boolean = true
    ) : UiContract.State

    sealed class Intent : UiContract.Intent {

        data object Init : Intent()

        data object LoadTrip : Intent()
    }

    sealed class Effect : UiContract.Effect
}
