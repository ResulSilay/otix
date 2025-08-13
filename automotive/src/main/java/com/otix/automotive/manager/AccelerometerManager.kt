package com.otix.automotive.manager

import androidx.car.app.hardware.CarHardwareManager
import androidx.car.app.hardware.common.OnCarDataAvailableListener
import androidx.car.app.hardware.info.Accelerometer
import androidx.car.app.hardware.info.CarSensors
import com.otix.automotive.ext.isSuccess
import com.otix.shared.domain.model.AccelerometerDto
import com.otix.shared.domain.model.VehicleValue
import java.util.concurrent.Executor

class AccelerometerManager(
    private val manager: CarHardwareManager,
    private val executor: Executor
) : VehicleInfoManager<AccelerometerDto> {

    private var listener: OnCarDataAvailableListener<Accelerometer>? = null

    override fun registerListener(callback: (AccelerometerDto) -> Unit) {
        listener = OnCarDataAvailableListener { accelerometer ->
            callback(
                AccelerometerDto(
                    forces = VehicleValue(
                        value = accelerometer.forces.value,
                        status = accelerometer.forces.status.isSuccess()
                    ),
                    rawString = buildString {
                        append("x: ${accelerometer.forces.value?.get(0)}, ")
                        append("y: ${accelerometer.forces.value?.get(1)}, ")
                        append("z: ${accelerometer.forces.value?.get(2)}")
                    }
                )
            )
        }

        listener?.run {
            manager.carSensors.addAccelerometerListener(
                CarSensors.UPDATE_RATE_FASTEST,
                executor,
                this
            )
        }
    }

    override fun unregisterListener() {
        listener?.let(manager.carSensors::removeAccelerometerListener)
        listener = null
    }
}