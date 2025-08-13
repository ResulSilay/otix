package com.otix.shared.domain.local.usecase

import com.otix.shared.domain.local.repository.VehicleRepository
import com.otix.shared.domain.model.Vehicle
import com.otix.shared.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetVehiclesUseCase(
    private val repository: VehicleRepository,
) {

    operator fun invoke(): Flow<Resource<List<Vehicle>>> = flow {
        run {
            emit(Resource.Loading(isLoading = true))
        }.runCatching {
            repository.getAll()
        }.onSuccess { result ->
            emit(Resource.Success(data = result))
        }.onFailure { error ->
            emit(Resource.Failure(message = error.message.orEmpty()))
        }.run {
            emit(Resource.Loading(isLoading = false))
        }
    }
}
