package com.otix.shared.data.local.source.vehicle

import com.otix.shared.data.local.entity.VehicleEntity

interface VehicleLocalDataSource {

    fun get(id: String): VehicleEntity

    fun getLast(): VehicleEntity

    fun getAll(): List<VehicleEntity>

    suspend fun remove(vehicleId: String)

    suspend fun save(vehicle: VehicleEntity)
}
