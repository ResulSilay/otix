package com.otix.shared.data.local.repository

import com.otix.shared.data.local.mapper.vehicle.VehicleMapper
import com.otix.shared.data.local.source.vehicle.VehicleLocalDataSource
import com.otix.shared.domain.local.repository.VehicleRepository
import com.otix.shared.domain.model.Vehicle

internal class VehicleRepositoryImpl(
    private val localDataSource: VehicleLocalDataSource,
    private val vehicleMapper: VehicleMapper,
) : VehicleRepository {

    override fun get(id: String): Vehicle = localDataSource.get(id = id).let(vehicleMapper::fromVehicleEntity)

    override fun getLast(): Vehicle = localDataSource.getLast().let(vehicleMapper::fromVehicleEntity)

    override fun getAll(): List<Vehicle> = localDataSource.getAll().map(vehicleMapper::fromVehicleEntity)

    override suspend fun remove(vehicleId: String) = localDataSource.remove(vehicleId = vehicleId)

    override suspend fun save(vehicle: Vehicle) {
        localDataSource.save(vehicle = vehicleMapper.toVehicleEntity(vehicle = vehicle))
    }
}
