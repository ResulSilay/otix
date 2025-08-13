package com.otix.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder

class CarStatusService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {

        const val ACTION_CAR_APP_STATUS = "com.otix.CAR_APP_STATUS"
        const val EXTRA_CAR_ACTIVE = "EXTRA_CAR_ACTIVE"

        fun sendCarStatus(context: Context, isActive: Boolean) {
            val intent = Intent(ACTION_CAR_APP_STATUS)
            intent.putExtra(EXTRA_CAR_ACTIVE, isActive)
            context.sendBroadcast(intent)
        }
    }
}
