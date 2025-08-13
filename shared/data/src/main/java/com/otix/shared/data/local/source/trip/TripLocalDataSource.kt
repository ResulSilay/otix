package com.otix.shared.data.local.source.trip

import com.otix.shared.data.local.entity.TripEntity

interface TripLocalDataSource {

    fun get(id: Long): TripEntity

    fun getAll(vehicleId: String): List<TripEntity>

    suspend fun save(trip: TripEntity)
}
