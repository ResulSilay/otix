package com.otix.automotive.manager

import android.annotation.SuppressLint
import androidx.car.app.hardware.CarHardwareManager
import androidx.car.app.hardware.common.OnCarDataAvailableListener
import androidx.car.app.hardware.info.EvStatus
import com.otix.automotive.ext.isSuccess
import com.otix.shared.domain.model.EvStatusDto
import com.otix.shared.domain.model.VehicleValue
import java.util.concurrent.Executor

@SuppressLint("UnsafeOptInUsageError")
class EvStatusManager(
    private val manager: CarHardwareManager,
    private val executor: Executor
) : VehicleInfoManager<EvStatusDto> {

    private var listener: OnCarDataAvailableListener<EvStatus>? = null

    override fun registerListener(callback: (EvStatusDto) -> Unit) {
        listener = OnCarDataAvailableListener { evStatus ->
            callback(
                EvStatusDto(
                    evChargePortOpen = VehicleValue(
                        value = evStatus.evChargePortOpen.value,
                        status = evStatus.evChargePortOpen.status.isSuccess()
                    ),
                    evChargePortConnected = VehicleValue(
                        value = evStatus.evChargePortConnected.value,
                        status = evStatus.evChargePortConnected.status.isSuccess()
                    ),
                    rawString = evStatus.toString()
                )
            )
        }

        listener?.run {
            manager.carInfo.addEvStatusListener(executor, this)
        }
    }

    override fun unregisterListener() {
        listener?.let(manager.carInfo::removeEvStatusListener)
        listener = null
    }
}