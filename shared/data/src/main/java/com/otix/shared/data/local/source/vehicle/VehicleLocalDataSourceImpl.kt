package com.otix.shared.data.local.source.vehicle

import com.otix.shared.data.local.OtixDatabase
import com.otix.shared.data.local.entity.VehicleEntity
import com.otix.shared.data.local.entity.VehicleInfoEntity
import kotlinx.serialization.json.Json

class VehicleLocalDataSourceImpl(
    database: OtixDatabase,
    private val json: Json,
) : VehicleLocalDataSource {

    private val queries = database.vehicleQueries

    override fun get(id: String): VehicleEntity = queries.get(id = id)
        .executeAsOneOrNull()
        .let { entity ->
            VehicleEntity(
                id = entity?.id.orEmpty(),
                info = if (entity != null && entity.info?.isNotBlank() == true) {
                    json.decodeFromString<VehicleInfoEntity>(entity.info)
                } else {
                    VehicleInfoEntity()
                }
            )
        }

    override fun getLast(): VehicleEntity = queries.getLast()
        .executeAsOneOrNull()
        .let { entity ->
            VehicleEntity(
                id = entity?.id.orEmpty(),
                info = if (entity != null && entity.info?.isNotBlank() == true) {
                    json.decodeFromString<VehicleInfoEntity>(entity.info)
                } else {
                    VehicleInfoEntity()
                }
            )
        }

    override fun getAll(): List<VehicleEntity> = queries.getAll()
        .executeAsList()
        .map { entity ->
            VehicleEntity(
                id = entity.id.uppercase(),
                info = if (entity.info?.isNotBlank() == true) {
                    json.decodeFromString<VehicleInfoEntity>(entity.info)
                } else {
                    VehicleInfoEntity()
                }
            )
        }

    override suspend fun remove(vehicleId: String) {
        queries.delete(id = vehicleId)
    }

    override suspend fun save(vehicle: VehicleEntity) {
        queries.save(
            id = vehicle.id,
            info = json.encodeToString(
                serializer = VehicleInfoEntity.serializer(),
                value = vehicle.info
            )
        )
    }
}