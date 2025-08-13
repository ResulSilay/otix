package com.otix.automotive.manager

import androidx.annotation.OptIn
import androidx.car.app.annotations.ExperimentalCarApi
import androidx.car.app.hardware.CarHardwareManager
import androidx.car.app.hardware.climate.CarClimateStateCallback
import androidx.car.app.hardware.climate.RegisterClimateStateRequest
import androidx.car.app.hardware.common.CarValue
import com.otix.automotive.ext.isSuccess
import com.otix.shared.domain.model.ClimateDto
import java.util.concurrent.Executor

@OptIn(ExperimentalCarApi::class)
class ClimateManager(
    private val manager: CarHardwareManager,
    private val executor: Executor,
) : VehicleInfoManager<ClimateDto> {

    private var climateCallback: CarClimateStateCallback? = null
    private var latestState = ClimateDto()

    override fun registerListener(callback: (ClimateDto) -> Unit) {
        climateCallback = object : CarClimateStateCallback {

            private fun buildRawString(state: ClimateDto): String = buildString {
                append("hvacPowerState:${state.hvacPowerState?.value},")
                append("hvacAcState:${state.hvacAcState?.value},")
                append("hvacMaxAcModeState:${state.hvacMaxAcModeState?.value},")
                append("hvacCabinTemperature:${state.hvacCabinTemperature?.value},")
                append("fanSpeedLevel:${state.fanSpeedLevel?.value},")
                append("fanDirection:${state.fanDirection?.value},")
                append("seatTemperatureLevel:${state.seatTemperatureLevel?.value},")
                append("seatVentilationLevel:${state.seatVentilationLevel?.value},")
                append("steeringWheelHeatState:${state.steeringWheelHeatState?.value},")
                append("hvacRecirculationState:${state.hvacRecirculationState?.value},")
                append("hvacAutoRecirculationState:${state.hvacAutoRecirculationState?.value},")
                append("hvacAutoModeState:${state.hvacAutoModeState?.value},")
                append("hvacDualModeState:${state.hvacDualModeState?.value},")
                append("defrosterState:${state.defrosterState?.value},")
                append("maxDefrosterState:${state.maxDefrosterState?.value},")
                append("electricDefrosterState:${state.electricDefrosterState?.value}")
            }

            override fun onHvacPowerStateAvailable(hvacPowerState: CarValue<Boolean>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(hvacPowerState = hvacPowerState.toCarValue())
                )
            }

            override fun onHvacAcStateAvailable(hvacAcState: CarValue<Boolean>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(hvacAcState = hvacAcState.toCarValue())
                )
            }

            override fun onHvacMaxAcModeStateAvailable(hvacMaxAcModeState: CarValue<Boolean>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(hvacMaxAcModeState = hvacMaxAcModeState.toCarValue())
                )
            }

            override fun onCabinTemperatureStateAvailable(hvacCabinTemperature: CarValue<Float>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(hvacCabinTemperature = hvacCabinTemperature.toCarValue())
                )
            }

            override fun onFanSpeedLevelStateAvailable(fanSpeedLevel: CarValue<Int>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(fanSpeedLevel = fanSpeedLevel.toCarValue())
                )
            }

            override fun onFanDirectionStateAvailable(fanDirection: CarValue<Int>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(fanDirection = fanDirection.toCarValue())
                )
            }

            override fun onSeatTemperatureLevelStateAvailable(seatTemperatureLevel: CarValue<Int>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(seatTemperatureLevel = seatTemperatureLevel.toCarValue())
                )
            }

            override fun onSeatVentilationLevelStateAvailable(seatVentilationLevel: CarValue<Int>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(seatVentilationLevel = seatVentilationLevel.toCarValue())
                )
            }

            override fun onSteeringWheelHeatStateAvailable(steeringWheelHeatState: CarValue<Boolean>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(steeringWheelHeatState = steeringWheelHeatState.toCarValue())
                )
            }

            override fun onHvacRecirculationStateAvailable(hvacRecirculationState: CarValue<Boolean>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(hvacRecirculationState = hvacRecirculationState.toCarValue())
                )
            }

            override fun onHvacAutoRecirculationStateAvailable(hvacAutoRecirculationState: CarValue<Boolean>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(hvacAutoRecirculationState = hvacAutoRecirculationState.toCarValue())
                )
            }

            override fun onHvacAutoModeStateAvailable(hvacAutoModeState: CarValue<Boolean>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(hvacAutoModeState = hvacAutoModeState.toCarValue())
                )
            }

            override fun onHvacDualModeStateAvailable(hvacDualModeState: CarValue<Boolean>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(hvacDualModeState = hvacDualModeState.toCarValue())
                )
            }

            override fun onDefrosterStateAvailable(defrosterState: CarValue<Boolean>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(defrosterState = defrosterState.toCarValue())
                )
            }

            override fun onMaxDefrosterStateAvailable(maxDefrosterState: CarValue<Boolean>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(maxDefrosterState = maxDefrosterState.toCarValue())
                )
            }

            override fun onElectricDefrosterStateAvailable(electricDefrosterState: CarValue<Boolean>) {
                updateStateAndCallback(
                    callback = callback,
                    state = latestState.copy(electricDefrosterState = electricDefrosterState.toCarValue())
                )
            }

            private fun updateStateAndCallback(
                callback: (ClimateDto) -> Unit,
                state: ClimateDto,
            ) {
                latestState = state.copy(rawString = buildRawString(state = state))
                callback(latestState)
            }
        }

        val request = RegisterClimateStateRequest
            .Builder(true)
            .build()

        climateCallback?.run {
            manager.carClimate.registerClimateStateCallback(executor, request, this)
        }
    }

    override fun unregisterListener() {
        climateCallback?.let(manager.carClimate::unregisterClimateStateCallback)
        climateCallback = null
    }
}

private fun <T> CarValue<T>.toCarValue() = com.otix.shared.domain.model.VehicleValue(
    value = value,
    status = status.isSuccess()
)