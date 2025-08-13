package com.otix.mobile.feature.connect.presentation.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import com.otix.service.CarStatusService

@Composable
internal fun RegisterCarAppStatusReceiver(
    onStatusChange: (Boolean) -> Unit,
) {
    val context = LocalContext.current
    val currentOnStatusChange by rememberUpdatedState(onStatusChange)

    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == CarStatusService.ACTION_CAR_APP_STATUS) {
                    val isActive = intent.getBooleanExtra(CarStatusService.EXTRA_CAR_ACTIVE, false)
                    currentOnStatusChange(isActive)
                }
            }
        }

        val filter = IntentFilter(CarStatusService.ACTION_CAR_APP_STATUS)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(receiver, filter, Context.RECEIVER_EXPORTED)
        } else {
            @SuppressLint("UnspecifiedRegisterReceiverFlag")
            context.registerReceiver(receiver, filter)
        }

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }
}