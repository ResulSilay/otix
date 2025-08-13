package com.otix.shared.domain.local.usecase

import com.otix.shared.domain.local.repository.PropertyRepository
import com.otix.shared.domain.model.VehicleProperty

class SavePropertyUseCase(
    private val repository: PropertyRepository,
) {

    suspend operator fun invoke(
        property: VehicleProperty,
    ): Boolean = repository.save(property = property)
}
