package com.otix.automotive.manager

import androidx.car.app.hardware.CarHardwareManager
import com.otix.automotive.ext.isSuccess
import com.otix.shared.domain.model.ModelDto
import com.otix.shared.domain.model.VehicleValue
import java.util.concurrent.Executor

class ModelFetcher(
    private val manager: CarHardwareManager,
    private val executor: Executor
) {
    fun fetchModel(callback: (ModelDto) -> Unit) {
        manager.carInfo.fetchModel(executor) { model ->
            callback(
                ModelDto(
                    manufacturer = VehicleValue(
                        value = model.manufacturer.value,
                        status = model.manufacturer.status.isSuccess()
                    ),
                    name = VehicleValue(
                        value = model.name.value,
                        status = model.name.status.isSuccess()
                    ),
                    year = VehicleValue(
                        value = model.year.value,
                        status = model.year.status.isSuccess()
                    ),
                    rawString = model.toString()
                )
            )
        }
    }
}