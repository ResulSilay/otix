package com.otix.automotive.service

import android.content.Intent
import androidx.car.app.Screen
import androidx.car.app.Session
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.otix.automotive.screen.permission.PermissionScreen
import com.otix.service.CarStatusService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

internal class MainSession : Session(), DefaultLifecycleObserver {

    private var carStatusJob: Job? = null

    override fun onCreateScreen(intent: Intent): Screen {
        startStatusJob()
        return PermissionScreen(carContext = carContext)
    }

    private fun startStatusJob() {
        carStatusJob = CoroutineScope(context = Dispatchers.Default).launch {
            while (isActive) {
                CarStatusService.sendCarStatus(carContext, isActive = true)
                delay(timeMillis = CAR_STATUS_DELAY)
            }
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        carStatusJob?.cancel()
        CarStatusService.sendCarStatus(carContext, isActive = false)
    }

    private companion object {

        const val CAR_STATUS_DELAY = 3000L
    }
}
