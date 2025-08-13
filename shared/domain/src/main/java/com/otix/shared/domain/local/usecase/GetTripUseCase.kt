package com.otix.shared.domain.local.usecase

import com.otix.shared.domain.local.repository.TripRepository
import com.otix.shared.domain.model.Trip
import com.otix.shared.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTripUseCase(
    private val repository: TripRepository,
) {

    operator fun invoke(id: Long): Flow<Resource<Trip>> = flow {
        run {
            emit(Resource.Loading(isLoading = true))
        }.runCatching {
            repository.get(id = id)
        }.onSuccess { result ->
            emit(Resource.Success(data = result))
        }.onFailure { error ->
            emit(Resource.Failure(message = error.message.orEmpty()))
        }.run {
            emit(Resource.Loading(isLoading = false))
        }
    }
}
