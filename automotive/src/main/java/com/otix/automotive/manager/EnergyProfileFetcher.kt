package com.otix.automotive.manager

import androidx.car.app.hardware.CarHardwareManager
import com.otix.automotive.ext.isSuccess
import com.otix.shared.domain.model.EnergyProfileDto
import com.otix.shared.domain.model.VehicleValue
import java.util.concurrent.Executor

class EnergyProfileFetcher(
    private val manager: CarHardwareManager,
    private val executor: Executor
) {

    fun fetchEnergyProfile(callback: (EnergyProfileDto) -> Unit) {
        manager.carInfo.fetchEnergyProfile(executor) { energyProfile ->
            callback(
                EnergyProfileDto(
                    fuelTypes = VehicleValue(
                        value = energyProfile.fuelTypes.value,
                        status = energyProfile.fuelTypes.status.isSuccess()
                    ),
                    evConnectorTypes = VehicleValue(
                        value = energyProfile.evConnectorTypes.value,
                        status = energyProfile.evConnectorTypes.status.isSuccess()
                    ),
                    rawString = energyProfile.toString()
                )
            )
        }
    }
}