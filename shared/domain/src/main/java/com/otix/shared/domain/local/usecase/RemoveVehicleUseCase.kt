package com.otix.shared.domain.local.usecase

import com.otix.shared.domain.local.repository.VehicleRepository
import com.otix.shared.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoveVehicleUseCase(
    private val repository: VehicleRepository,
) {

    operator fun invoke(vehicleId: String): Flow<Resource<Unit>> = flow {
        run {
            emit(Resource.Loading(isLoading = true))
        }.runCatching {
            repository.remove(vehicleId = vehicleId)
        }.onSuccess { result ->
            emit(Resource.Success(data = result))
        }.onFailure { error ->
            emit(Resource.Failure(message = error.message.orEmpty()))
        }.run {
            emit(Resource.Loading(isLoading = false))
        }
    }
}
