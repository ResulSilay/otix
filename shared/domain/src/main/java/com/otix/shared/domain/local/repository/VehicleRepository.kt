package com.otix.shared.domain.local.repository

import com.otix.shared.domain.model.Vehicle

interface VehicleRepository {

    fun get(id: String): Vehicle

    fun getLast(): Vehicle

    fun getAll(): List<Vehicle>

    suspend fun remove(vehicleId: String)

    suspend fun save(vehicle: Vehicle)
}