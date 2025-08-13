package com.otix.wear.presentation

import androidx.lifecycle.viewModelScope
import com.otix.core.architecture.BaseViewModel
import com.otix.shared.domain.local.usecase.GetLastVehicleUseCase
import com.otix.shared.domain.resource.onSuccess
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val getLastCar: GetLastVehicleUseCase,
) : BaseViewModel<MainContract.State, MainContract.Intent, MainContract.Effect>(
    initialState = MainContract.State()
) {

    override suspend fun reduce(intent: MainContract.Intent) = reduceEvent(intent = intent)

    internal fun getCar() {
        viewModelScope.launch {
            getLastCar().collectLatest { resource ->
                resource.onSuccess { result ->
                    setState {
                        copy(vehicleInfo = result.info)
                    }
                }
            }
        }
    }
}