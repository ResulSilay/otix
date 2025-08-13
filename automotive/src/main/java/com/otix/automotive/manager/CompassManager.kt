package com.otix.automotive.manager

import androidx.car.app.hardware.CarHardwareManager
import androidx.car.app.hardware.common.OnCarDataAvailableListener
import androidx.car.app.hardware.info.CarSensors
import androidx.car.app.hardware.info.Compass
import com.otix.automotive.ext.isSuccess
import com.otix.shared.domain.model.CompassDto
import com.otix.shared.domain.model.VehicleValue
import java.util.concurrent.Executor

class CompassManager(
    private val manager: CarHardwareManager,
    private val executor: Executor
) : VehicleInfoManager<CompassDto> {

    private var listener: OnCarDataAvailableListener<Compass>? = null

    override fun registerListener(callback: (CompassDto) -> Unit) {
        listener = OnCarDataAvailableListener { compass ->
            callback(
                CompassDto(
                    orientations = VehicleValue(
                        value = compass.orientations.value,
                        status = compass.orientations.status.isSuccess()
                    ),
                    rawString = buildString {
                        append("Bearing: ${compass.orientations.value?.get(0)}, ")
                        append("Pitch: ${compass.orientations.value?.get(1)}, ")
                        append("Roll: ${compass.orientations.value?.get(2)}")
                    }
                )
            )
        }

        listener?.run {
            manager.carSensors.addCompassListener(
                CarSensors.UPDATE_RATE_FASTEST,
                executor,
                this
            )
        }
    }

    override fun unregisterListener() {
        listener?.let(manager.carSensors::removeCompassListener)
        listener = null
    }
}