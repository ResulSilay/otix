package com.otix.shared.domain.local.usecase

import com.otix.shared.domain.local.repository.TripRepository
import com.otix.shared.domain.model.Trip
import com.otix.shared.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTripListUseCase(
    private val repository: TripRepository,
) {

    operator fun invoke(vehicleId: String): Flow<Resource<List<Trip>>> = flow {
        run {
            emit(Resource.Loading(isLoading = true))
        }.runCatching {
            repository.getAll(vehicleId = vehicleId)
        }.onSuccess { result ->
            emit(Resource.Success(data = result))
        }.onFailure { error ->
            emit(Resource.Failure(message = error.message.orEmpty()))
        }.run {
            emit(Resource.Loading(isLoading = false))
        }
    }
}
