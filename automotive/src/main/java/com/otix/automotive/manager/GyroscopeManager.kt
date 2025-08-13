package com.otix.automotive.manager

import androidx.car.app.hardware.CarHardwareManager
import androidx.car.app.hardware.common.OnCarDataAvailableListener
import androidx.car.app.hardware.info.CarSensors
import androidx.car.app.hardware.info.Gyroscope
import com.otix.automotive.ext.isSuccess
import com.otix.shared.domain.model.GyroscopeDto
import com.otix.shared.domain.model.VehicleValue
import java.util.concurrent.Executor

class GyroscopeManager(
    private val manager: CarHardwareManager,
    private val executor: Executor
) : VehicleInfoManager<GyroscopeDto> {

    private var listener: OnCarDataAvailableListener<Gyroscope>? = null

    override fun registerListener(callback: (GyroscopeDto) -> Unit) {
        listener = OnCarDataAvailableListener { gyroscope ->
            callback(
                GyroscopeDto(
                    rotations = VehicleValue(
                        value = gyroscope.rotations.value,
                        status = gyroscope.rotations.status.isSuccess()
                    ),
                    rawString = buildString {
                        append("x: ${gyroscope.rotations.value?.get(0)}, ")
                        append("y: ${gyroscope.rotations.value?.get(1)}, ")
                        append("z: ${gyroscope.rotations.value?.get(2)}")
                    }
                )
            )
        }

        listener?.run {
            manager.carSensors.addGyroscopeListener(
                CarSensors.UPDATE_RATE_FASTEST,
                executor,
                this
            )
        }
    }

    override fun unregisterListener() {
        listener?.let(manager.carSensors::removeGyroscopeListener)
        listener = null
    }
}