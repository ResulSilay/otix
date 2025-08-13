package com.otix.shared.domain.local.repository

import com.otix.shared.domain.model.Trip

interface TripRepository {

    fun get(id: Long): Trip

    fun getAll(vehicleId: String): List<Trip>

    suspend fun save(trip: Trip)
}