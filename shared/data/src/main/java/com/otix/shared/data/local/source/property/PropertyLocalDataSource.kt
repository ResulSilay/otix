package com.otix.shared.data.local.source.property

interface PropertyLocalDataSource {

    fun getAll(vehicleId: String): Map<String, String>

    fun get(vehicleId: String, key: String): String?

    suspend fun save(vehicleId: String, key: String, value: String): Boolean

    suspend fun deleteAll()

    suspend fun deleteByVehicleId(vehicleId: String)
}
