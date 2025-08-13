package com.otix.automotive.manager

import androidx.car.app.hardware.CarHardwareManager
import androidx.car.app.hardware.common.OnCarDataAvailableListener
import androidx.car.app.hardware.info.TollCard
import com.otix.automotive.ext.isSuccess
import com.otix.shared.domain.model.TollCardDto
import com.otix.shared.domain.model.VehicleValue
import java.util.concurrent.Executor

class TollCardManager(
    private val manager: CarHardwareManager,
    private val executor: Executor
) : VehicleInfoManager<TollCardDto> {

    private var listener: OnCarDataAvailableListener<TollCard>? = null

    override fun registerListener(callback: (TollCardDto) -> Unit) {
        listener = OnCarDataAvailableListener { tollCard ->
            callback(
                TollCardDto(
                    cardState = VehicleValue(
                        value = tollCard.cardState.value,
                        status = tollCard.cardState.status.isSuccess()
                    ),
                    rawString = tollCard.toString()
                )
            )
        }

        listener?.run {
            manager.carInfo.addTollListener(executor, this)
        }
    }

    override fun unregisterListener() {
        listener?.let(manager.carInfo::removeTollListener)
        listener = null
    }
}