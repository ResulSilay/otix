package com.otix.shared.data.local.mapper.vehicle

import com.otix.shared.data.local.entity.VehicleEntity
import com.otix.shared.domain.model.Vehicle

interface VehicleMapper {

    fun toVehicleEntity(vehicle: Vehicle): VehicleEntity

    fun fromVehicleEntity(entity: VehicleEntity): Vehicle
}
