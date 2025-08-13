package com.otix.shared.domain.local.usecase

import com.otix.shared.domain.local.repository.TripRepository
import com.otix.shared.domain.model.Trip

class SaveTripUseCase(
    private val repository: TripRepository
) {

    suspend operator fun invoke(trip: Trip) = repository.save(trip = trip)
}
