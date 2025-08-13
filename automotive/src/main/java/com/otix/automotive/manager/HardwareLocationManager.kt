package com.otix.automotive.manager

import androidx.car.app.hardware.CarHardwareManager
import androidx.car.app.hardware.common.OnCarDataAvailableListener
import androidx.car.app.hardware.info.CarHardwareLocation
import androidx.car.app.hardware.info.CarSensors
import com.otix.automotive.ext.isSuccess
import com.otix.shared.domain.model.HardwareLocationDto
import com.otix.shared.domain.model.VehicleValue
import java.util.concurrent.Executor

class HardwareLocationManager(
    private val manager: CarHardwareManager,
    private val executor: Executor
) : VehicleInfoManager<HardwareLocationDto> {

    private var listener: OnCarDataAvailableListener<CarHardwareLocation>? = null

    override fun registerListener(callback: (HardwareLocationDto) -> Unit) {
        listener = OnCarDataAvailableListener { hardwareLocation ->
            callback(
                HardwareLocationDto(
                    location = VehicleValue(
                        value = hardwareLocation.location.value,
                        status = hardwareLocation.location.status.isSuccess()
                    ),
                    rawString = hardwareLocation.toString()
                )
            )
        }

        listener?.run {
            manager.carSensors.addCarHardwareLocationListener(
                CarSensors.UPDATE_RATE_FASTEST,
                executor,
                this
            )
        }
    }

    override fun unregisterListener() {
        listener?.let(manager.carSensors::removeCarHardwareLocationListener)
        listener = null
    }
}