package com.otix.automotive.manager

import androidx.car.app.annotations.ExperimentalCarApi
import androidx.car.app.hardware.CarHardwareManager
import androidx.car.app.hardware.common.OnCarDataAvailableListener
import androidx.car.app.hardware.info.EnergyLevel
import com.otix.automotive.ext.isSuccess
import com.otix.shared.domain.model.EnergyLevelDto
import com.otix.shared.domain.model.VehicleValue
import com.otix.shared.domain.type.UnitType
import java.util.concurrent.Executor

class EnergyLevelManager(
    private val manager: CarHardwareManager,
    private val executor: Executor,
) : VehicleInfoManager<EnergyLevelDto> {

    private var listener: OnCarDataAvailableListener<EnergyLevel>? = null

    @ExperimentalCarApi
    override fun registerListener(callback: (EnergyLevelDto) -> Unit) {
        listener = OnCarDataAvailableListener { energyLevel ->
            callback(
                EnergyLevelDto(
                    batteryPercent = VehicleValue(
                        value = energyLevel.batteryPercent.value,
                        status = energyLevel.batteryPercent.status.isSuccess()
                    ),
                    distanceDisplayUnit = VehicleValue(
                        value = UnitType.fromCode(code = energyLevel.distanceDisplayUnit.value),
                        status = energyLevel.distanceDisplayUnit.status.isSuccess()
                    ),
                    energyIsLow = VehicleValue(
                        value = energyLevel.energyIsLow.value,
                        status = energyLevel.energyIsLow.status.isSuccess()
                    ),
                    fuelPercent = VehicleValue(
                        value = energyLevel.fuelPercent.value,
                        status = energyLevel.fuelPercent.status.isSuccess()
                    ),
                    fuelVolumeDisplayUnit = VehicleValue(
                        value = UnitType.fromCode(code = energyLevel.fuelVolumeDisplayUnit.value),
                        status = energyLevel.fuelVolumeDisplayUnit.status.isSuccess()
                    ),
                    rangeRemainingMeters = VehicleValue(
                        value = energyLevel.rangeRemainingMeters.value,
                        status = energyLevel.rangeRemainingMeters.status.isSuccess()
                    ),
                    rawString = energyLevel.toString()
                )
            )
        }

        listener?.run {
            manager.carInfo.addEnergyLevelListener(executor, this)
        }
    }

    override fun unregisterListener() {
        listener?.let(manager.carInfo::removeEnergyLevelListener)
        listener = null
    }
}