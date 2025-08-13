package com.otix.shared.data.local.source.trip

import com.otix.shared.data.local.OtixDatabase
import com.otix.shared.data.local.entity.TripDataEntity
import com.otix.shared.data.local.entity.TripEntity
import kotlinx.serialization.json.Json

class TripLocalDataSourceImpl(
    database: OtixDatabase,
    private val json: Json
) : TripLocalDataSource {

    private val queries = database.tripQueries

    override fun get(id: Long): TripEntity = queries.get(id = id)
        .executeAsOneOrNull()
        .let { entity ->
            TripEntity(
                id = entity?.id,
                vehicleId = entity?.vehicleId.orEmpty(),
                data = if (entity?.info != null) {
                    json.decodeFromString<TripDataEntity>(entity.info)
                } else {
                    TripDataEntity()
                }
            )
        }

    override fun getAll(vehicleId: String): List<TripEntity> = queries.getAllByVehicleId(vehicleId = vehicleId)
        .executeAsList()
        .map { entity ->
            TripEntity(
                id = entity.id,
                vehicleId = entity.vehicleId.orEmpty(),
                data = if (entity.info != null) {
                    json.decodeFromString<TripDataEntity>(entity.info)
                } else {
                    TripDataEntity()
                }
            )
        }

    override suspend fun save(trip: TripEntity) {
        queries.insert(
            id = trip.id,
            vehicleId = trip.vehicleId,
            info = json.encodeToString(
                serializer = TripDataEntity.serializer(),
                value = trip.data
            )
        )
    }
}