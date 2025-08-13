package com.otix.shared.domain.local.repository

import com.otix.shared.domain.model.VehicleProperty

interface PropertyRepository {

    fun get(vehicleId: String, key: String): VehicleProperty?

    fun getAll(vehicleId: String): List<VehicleProperty>

    suspend fun save(property: VehicleProperty): Boolean

    suspend fun deleteAll()

    suspend fun deleteByVehicleId(vehicleId: String)
}
