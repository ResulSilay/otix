package com.otix.shared.data.local.mapper.property

import com.otix.shared.data.local.entity.VehiclePropertyEntity
import com.otix.shared.domain.model.VehicleProperty

interface PropertyMapper {

    fun toEntity(property: VehicleProperty): VehiclePropertyEntity

    fun fromEntity(entity: VehiclePropertyEntity): VehicleProperty
}
