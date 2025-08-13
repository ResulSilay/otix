package com.otix.mobile.feature.settings.presentation.screen

import androidx.lifecycle.viewModelScope
import com.otix.core.architecture.BaseViewModel
import com.otix.shared.domain.local.usecase.GetVehiclesUseCase
import com.otix.shared.domain.local.usecase.RemoveVehicleUseCase
import com.otix.shared.domain.resource.onSuccess
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getVehicles: GetVehiclesUseCase,
    private val removeVehicle: RemoveVehicleUseCase,
) : BaseViewModel<SettingsContract.State, SettingsContract.Intent, SettingsContract.Effect>(
    initialState = SettingsContract.State()
) {

    override suspend fun reduce(intent: SettingsContract.Intent) = reduceEvent(intent = intent)

    internal fun loadVehicles() {
        viewModelScope.launch {
            getVehicles().collect { resource ->
                resource.onSuccess { result ->
                    setState {
                        copy(
                            vehicles = result.toPersistentList()
                        )
                    }
                }
            }
        }
    }

    internal fun removeAtVehicle(vehicleId: String) {
        viewModelScope.launch {
            removeVehicle(vehicleId = vehicleId).collect { resource ->
                resource.onSuccess { result ->
                    loadVehicles()
                }
            }
        }
    }
}
