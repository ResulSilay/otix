package com.otix.mobile.feature.trip.presentation.screen.detail

import androidx.lifecycle.viewModelScope
import com.otix.core.architecture.BaseViewModel
import com.otix.core.navigation.route.TripDetail
import com.otix.shared.domain.local.usecase.GetTripUseCase
import com.otix.shared.domain.resource.onLoading
import com.otix.shared.domain.resource.onSuccess
import com.otix.shared.presentation.mock.getTripsMock
import kotlinx.coroutines.launch

class TripDetailViewModel(
    private val args: TripDetail,
    private val getTrip: GetTripUseCase,
) : BaseViewModel<TripDetailContract.State, TripDetailContract.Intent, TripDetailContract.Effect>(
    initialState = TripDetailContract.State()
) {

    override suspend fun reduce(intent: TripDetailContract.Intent) = reduceEvent(intent = intent)

    internal fun init() {
        setState {
            copy(
                tripId = args.tripId,
                isDemoAccount = args.isDemoAccount
            )
        }
    }

    internal fun loadTrip() {
        if (args.isDemoAccount) {
            setState {
                copy(trip = getTripsMock().firstOrNull())
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
                            copy(trip = result)
                        }
                    }
            }
        }
    }
}
