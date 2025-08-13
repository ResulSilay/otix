package com.otix.mobile.feature.trip.presentation.screen.trip

import androidx.lifecycle.viewModelScope
import com.otix.core.architecture.BaseViewModel
import com.otix.shared.domain.local.usecase.GetTripListUseCase
import com.otix.shared.domain.resource.onLoading
import com.otix.shared.domain.resource.onSuccess
import com.otix.shared.presentation.mock.getTripsMock
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch

class TripViewModel(
    private val getTripList: GetTripListUseCase
) : BaseViewModel<TripContract.State, TripContract.Intent, TripContract.Effect>(
    initialState = TripContract.State()
) {

    override suspend fun reduce(intent: TripContract.Intent) = reduceEvent(intent = intent)

    internal fun loadTripList(vehicleId: String) {
        viewModelScope.launch {
            getTripList(vehicleId = vehicleId).collect { resource ->
                resource
                    .onLoading { isLoading ->
                        setState {
                            copy(isLoading = isLoading)
                        }
                    }
                    .onSuccess { result ->
                        setState {
                            copy(
                                tripList = result.toPersistentList(),
                            )
                        }
                    }
            }
        }
    }

    internal fun loadTripMock() {
        setState {
            copy(
                tripList = getTripsMock().toPersistentList(),
            )
        }
    }
}
