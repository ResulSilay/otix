package com.otix.mobile.feature.trip.presentation.screen.map

import com.otix.core.architecture.UiContract
import com.otix.shared.domain.model.GeoLocation
import com.otix.shared.domain.model.TripInfo
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

object TripMapContract {

    data class State(
        val tripInfo: TripInfo? = null,
        val routePoints: PersistentList<GeoLocation> = persistentListOf(),
        val isLoading: Boolean = true
    ) : UiContract.State

    sealed class Intent : UiContract.Intent {

        data object LoadRoutes : Intent()
    }

    sealed class Effect : UiContract.Effect
}
