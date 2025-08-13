package com.otix.shared.data.local.source.property

import com.otix.shared.data.local.OtixDatabase

class PropertyLocalDataSourceImpl(
    database: OtixDatabase,
) : PropertyLocalDataSource {

    private val queries = database.propertyQueries

    override fun getAll(vehicleId: String): Map<String, String> =
        queries.getAll(vehicleId = vehicleId)
            .executeAsList()
            .associate { it.vehicleKey to it.vehicleValue.orEmpty() }

    override fun get(vehicleId: String, key: String): String? =
        queries.get(
            vehicleId = vehicleId,
            vehicleKey = key
        ).executeAsOneOrNull()?.vehicleValue

    override suspend fun save(vehicleId: String, key: String, value: String): Boolean {
        return queries.save(
            vehicleId = vehicleId,
            vehicleKey = key,
            vehicleValue = value
        ).value > 0
    }

    override suspend fun deleteAll() {
        queries.deleteAll()
    }

    override suspend fun deleteByVehicleId(vehicleId: String) {
        queries.deleteByVehicleId(vehicleId = vehicleId)
    }
}
