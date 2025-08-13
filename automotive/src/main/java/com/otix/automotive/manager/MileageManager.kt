package com.otix.automotive.manager

import androidx.car.app.hardware.CarHardwareManager
import androidx.car.app.hardware.common.OnCarDataAvailableListener
import androidx.car.app.hardware.info.Mileage
import com.otix.automotive.ext.isSuccess
import com.otix.shared.domain.model.MileageDto
import com.otix.shared.domain.model.VehicleValue
import com.otix.shared.domain.type.UnitType
import java.util.concurrent.Executor

class MileageManager(
    private val manager: CarHardwareManager,
    private val executor: Executor
) : VehicleInfoManager<MileageDto> {

    private var listener: OnCarDataAvailableListener<Mileage>? = null

    override fun registerListener(callback: (MileageDto) -> Unit) {
        listener = OnCarDataAvailableListener { mileage ->
            callback(
                MileageDto(
                    distanceDisplayUnit = VehicleValue(
                        value = UnitType.fromCode(code = mileage.distanceDisplayUnit.value),
                        status = mileage.distanceDisplayUnit.status.isSuccess()
                    ),
                    rawString = mileage.toString()
                )
            )
        }
        listener?.run {
            manager.carInfo.addMileageListener(executor, this)
        }
    }

    override fun unregisterListener() {
        listener?.let(manager.carInfo::removeMileageListener)
        listener = null
    }
}