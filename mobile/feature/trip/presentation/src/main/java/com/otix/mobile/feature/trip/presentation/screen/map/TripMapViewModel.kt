package com.otix.mobile.feature.trip.presentation.screen.map

import androidx.lifecycle.viewModelScope
import com.otix.core.architecture.BaseViewModel
import com.otix.core.navigation.route.TripMap
import com.otix.shared.domain.local.usecase.GetTripUseCase
import com.otix.shared.domain.resource.onLoading
import com.otix.shared.domain.resource.onSuccess
import com.otix.shared.presentation.mock.getTripsMock
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch

class TripMapViewModel(
    private val args: TripMap,
    private val getTrip: GetTripUseCase,
) : BaseViewModel<TripMapContract.State, TripMapContract.Intent, TripMapContract.Effect>(
    initialState = TripMapContract.State()
) {

    override suspend fun reduce(intent: TripMapContract.Intent) = reduceEvent(intent = intent)

    internal fun loadRoutes() {
        if (args.isDemoAccount) {
            setState {
                copy(
                    tripInfo = getTripsMock().first().info,
                    routePoints = getTripsMock().first().info.routePoints.toPersistentList()
                )
            }
            return
        }

        viewModelScope.launch {
            getTrip(id = args.tripId).collect { resource ->
                resource
                    .onLoading { isLoading ->
                        setState {
                            copy(isLoading = isLoading)
                        }
                    }
                    .onSuccess { result ->
                        setState {
                            copy(
                                tripInfo = result.info,
                                routePoints = result.info.routePoints.toPersistentList()
                            )
                        }
                    }
            }
        }
    }
}
