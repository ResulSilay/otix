package com.otix.automotive.screen.permission

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.CarIcon
import androidx.car.app.model.Header
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Template
import com.otix.automotive.screen.home.HomeScreen
import com.otix.core.resources.alias.Str
import com.otix.core.resources.strings.Strings
import com.otix.service.CarStatusService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class PermissionScreen(carContext: CarContext) : Screen(carContext), KoinComponent {

    private val strings: Strings by inject()
    private val requiredPermissions = getAutoPermissions()

    init {
        requestPermissions(onStatus = ::sendCarStatus)
    }

    override fun onGetTemplate(): Template =
        MessageTemplate.Builder(strings[Str.auto_permission_page_description])
            .setHeader(
                Header.Builder()
                    .setTitle(strings[Str.auto_permission_page_title])
                    .build()
            )
            .addAction(
                Action.Builder()
                    .setTitle(strings[Str.auto_permission_page_request_button_text])
                    .setOnClickListener {
                        requestPermissions(
                            isPop = false,
                            onStatus = {
                                sendCarStatus()
                            }
                        )
                    }
                    .build()
            )
            .addAction(
                Action.Builder()
                    .setTitle(strings[Str.vehicle_demo_account_button_text])
                    .setOnClickListener {
                        screenManager.push(HomeScreen(carContext = carContext))
                    }
                    .build()
            )
            .setIcon(CarIcon.APP_ICON)
            .build()

    private fun requestPermissions(isPop: Boolean = true, onStatus: () -> Unit) {
        runCatching {
            carContext.requestPermissions(requiredPermissions) { granted, _ ->
                when {
                    granted.containsAll(elements = requiredPermissions) -> {
                        onStatus.invoke()
                        screenManager.push(HomeScreen(carContext = carContext))
                    }

                    isPop -> screenManager.pop()

                    else -> Unit
                }
            }
        }.onFailure { error ->
            error.printStackTrace()
        }
    }

    private fun sendCarStatus() {
        CarStatusService.sendCarStatus(context = carContext, isActive = true)
    }
}
