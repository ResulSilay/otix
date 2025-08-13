package com.otix.shared.data.local.repository

import com.otix.shared.data.local.mapper.trip.TripMapper
import com.otix.shared.data.local.source.trip.TripLocalDataSource
import com.otix.shared.domain.local.repository.TripRepository
import com.otix.shared.domain.model.Trip

class TripRepositoryImpl(
    private val localDataSource: TripLocalDataSource,
    private val tripMapper: TripMapper
) : TripRepository {

    override fun get(id: Long): Trip = localDataSource.get(id = id).let(tripMapper::fromTripEntity)

    override fun getAll(vehicleId: String): List<Trip> = localDataSource.getAll(
        vehicleId = vehicleId
    ).map(tripMapper::fromTripEntity)

    override suspend fun save(trip: Trip) {
        localDataSource.save(trip = tripMapper.toTripEntity(trip = trip))
    }
}
