package com.otix.automotive.manager

import com.otix.shared.domain.type.VehicleState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VehicleStateManager(
    private val scope: CoroutineScope
) {
    private var job: Job? = null

    fun startPeriodicCheck(
        getSpeed: suspend () -> Float?,
        stateCallback: (VehicleState) -> Unit
    ) {
        job?.cancel()

        job = scope.launch {
            while (isActive) {
                val speed = withContext(context = Dispatchers.IO) {
                    getSpeed()
                }

                val vehicleState = when {
                    speed == null -> VehicleState.OFF
                    speed <= 0F -> VehicleState.PARKED
                    else -> VehicleState.DRIVING
                }

                withContext(context = Dispatchers.Main) {
                    stateCallback(vehicleState)
                }

                delay(timeMillis = 1_000L)
            }
        }
    }

    fun stopPeriodicCheck() {
        job?.cancel()
        job = null
    }
}
