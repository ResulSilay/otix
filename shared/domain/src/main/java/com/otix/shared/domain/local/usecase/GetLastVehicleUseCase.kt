package com.otix.shared.domain.local.usecase

import com.otix.shared.domain.local.repository.VehicleRepository
import com.otix.shared.domain.model.Vehicle
import com.otix.shared.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLastVehicleUseCase(
    private val repository: VehicleRepository
) {

    operator fun invoke(): Flow<Resource<Vehicle>> = flow {
        run {
            emit(Resource.Loading(isLoading = true))
        }.runCatching {
            repository.getLast()
        }.onSuccess { result ->
            emit(Resource.Success(data = result))
        }.onFailure { error ->
            emit(Resource.Failure(message = error.message.orEmpty()))
        }.run {
            emit(Resource.Loading(isLoading = false))
        }
    }
}
