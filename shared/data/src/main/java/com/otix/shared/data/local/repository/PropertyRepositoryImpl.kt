package com.otix.shared.data.local.repository

import com.otix.shared.data.local.entity.VehiclePropertyEntity
import com.otix.shared.data.local.mapper.property.PropertyMapper
import com.otix.shared.data.local.source.property.PropertyLocalDataSource
import com.otix.shared.domain.local.repository.PropertyRepository
import com.otix.shared.domain.model.VehicleProperty

internal class PropertyRepositoryImpl(
    private val localDataSource: PropertyLocalDataSource,
    private val propertyMapper: PropertyMapper,
) : PropertyRepository {

    override fun get(vehicleId: String, key: String): VehicleProperty? =
        localDataSource.get(vehicleId, key)?.let { value ->
            propertyMapper.fromEntity(
                entity = VehiclePropertyEntity(
                    vehicleId = vehicleId,
                    key = key,
                    value = value
                )
            )
        }

    override fun getAll(vehicleId: String): List<VehicleProperty> =
        localDataSource.getAll(vehicleId).map { (key, value) ->
            propertyMapper.fromEntity(
                entity = VehiclePropertyEntity(
                    vehicleId = vehicleId,
                    key = key,
                    value = value
                )
            )
        }

    override suspend fun save(property: VehicleProperty): Boolean {
        val entity = propertyMapper.toEntity(property = property)
        return localDataSource.save(
            vehicleId = entity.vehicleId,
            key = entity.key,
            value = entity.value.orEmpty()
        )
    }

    override suspend fun deleteAll() {
        localDataSource.deleteAll()
    }

    override suspend fun deleteByVehicleId(vehicleId: String) {
        localDataSource.deleteByVehicleId(vehicleId = vehicleId)
    }
}
