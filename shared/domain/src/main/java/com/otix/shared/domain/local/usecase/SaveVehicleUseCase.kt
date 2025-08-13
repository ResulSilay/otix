package com.otix.shared.domain.local.usecase

import com.otix.shared.domain.local.repository.VehicleRepository
import com.otix.shared.domain.model.Vehicle

class SaveVehicleUseCase(
    private val repository: VehicleRepository
) {

    suspend operator fun invoke(vehicle: Vehicle) = repository.save(vehicle = vehicle)
}
