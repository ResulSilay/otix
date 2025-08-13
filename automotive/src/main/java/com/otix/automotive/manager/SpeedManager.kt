package com.otix.automotive.manager

import androidx.car.app.hardware.CarHardwareManager
import androidx.car.app.hardware.common.OnCarDataAvailableListener
import androidx.car.app.hardware.info.Speed
import com.otix.automotive.ext.isSuccess
import com.otix.shared.domain.model.SpeedDto
import com.otix.shared.domain.model.VehicleValue
import com.otix.shared.domain.type.UnitType
import java.util.concurrent.Executor

class SpeedManager(
    private val manager: CarHardwareManager,
    private val executor: Executor
) : VehicleInfoManager<SpeedDto> {

    private var listener: OnCarDataAvailableListener<Speed>? = null

    override fun registerListener(callback: (SpeedDto) -> Unit) {
        listener = OnCarDataAvailableListener { speed ->
            callback(
                SpeedDto(
                    displaySpeedMetersPerSecond = VehicleValue(
                        value = speed.displaySpeedMetersPerSecond.value,
                        status = speed.displaySpeedMetersPerSecond.status.isSuccess(),
                    ),
                    rawSpeedMetersPerSecond = VehicleValue(
                        value = speed.rawSpeedMetersPerSecond.value,
                        status = speed.rawSpeedMetersPerSecond.status.isSuccess(),
                    ),
                    speedDisplayUnit = VehicleValue(
                        value = UnitType.fromCode(code = speed.speedDisplayUnit.value),
                        status = speed.speedDisplayUnit.status.isSuccess(),
                    ),
                    rawString = speed.toString()
                )
            )
        }

        listener?.run {
            manager.carInfo.addSpeedListener(executor, this)
        }
    }

    override fun unregisterListener() {
        listener?.let(manager.carInfo::removeSpeedListener)
        listener = null
    }
}