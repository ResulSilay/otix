package com.otix.shared.domain.local.usecase

import com.otix.shared.domain.local.repository.PropertyRepository
import com.otix.shared.domain.model.VehicleProperty
import com.otix.shared.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPropertyUseCase(
    private val repository: PropertyRepository,
) {

    operator fun invoke(vehicleId: String, key: String): Flow<Resource<VehicleProperty?>> = flow {
        run {
            emit(Resource.Loading(isLoading = true))
        }.runCatching {
            repository.get(vehicleId = vehicleId, key = key)
        }.onSuccess { result ->
            emit(Resource.Success(data = result))
        }.onFailure { error ->
            emit(Resource.Failure(message = error.message.orEmpty()))
        }.run {
            emit(Resource.Loading(isLoading = false))
        }
    }
}
