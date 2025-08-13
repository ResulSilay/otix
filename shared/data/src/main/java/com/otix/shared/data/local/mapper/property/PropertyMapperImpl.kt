package com.otix.shared.data.local.mapper.property

import com.otix.shared.data.local.entity.VehiclePropertyEntity
import com.otix.shared.domain.model.VehicleProperty

class PropertyMapperImpl : PropertyMapper {

    override fun toEntity(property: VehicleProperty): VehiclePropertyEntity =
        VehiclePropertyEntity(
            vehicleId = property.vehicleId,
            key = property.key,
            value = property.value
        )

    override fun fromEntity(entity: VehiclePropertyEntity): VehicleProperty =
        VehicleProperty(
            vehicleId = entity.vehicleId,
            key = entity.key,
            value = entity.value
        )
}
