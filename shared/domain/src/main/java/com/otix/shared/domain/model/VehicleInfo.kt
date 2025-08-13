package com.otix.shared.domain.model

import android.location.Location
import androidx.compose.runtime.Immutable
import com.otix.shared.domain.type.UnitType
import com.otix.shared.domain.type.VehicleState
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Immutable
data class Vehicle(
    val id: String,
    val info: VehicleInfo,
)

@Immutable
@Serializable
data class VehicleInfo(
    val vehicleId: String = "",
    val model: ModelDto = ModelDto(),
    val climate: ClimateDto = ClimateDto(),
    val energyProfile: EnergyProfileDto = EnergyProfileDto(),
    val energyLevel: EnergyLevelDto = EnergyLevelDto(),
    val speed: SpeedDto = SpeedDto(),
    val mileage: MileageDto = MileageDto(),
    val evStatus: EvStatusDto = EvStatusDto(),
    val tollCard: TollCardDto = TollCardDto(),
    val accelerometer: AccelerometerDto = AccelerometerDto(),
    val gyroscope: GyroscopeDto = GyroscopeDto(),
    val compass: CompassDto = CompassDto(),
    val hardwareLocation: HardwareLocationDto = HardwareLocationDto(),
    val state: VehicleState = VehicleState.IDLE
)

@Immutable
@Serializable
data class ModelDto(
    val manufacturer: VehicleValue<String> = VehicleValue(value = null, status = false),
    val name: VehicleValue<String> = VehicleValue(value = null, status = false),
    val year: VehicleValue<Int> = VehicleValue(value = null, status = false),
    val rawString: String = ""
)

@Immutable
@Serializable
data class EnergyProfileDto(
    val fuelTypes: VehicleValue<List<Int>> = VehicleValue(value = null, status = false),
    val evConnectorTypes: VehicleValue<List<Int>> = VehicleValue(value = null, status = false),
    val rawString: String = ""
)

@Immutable
@Serializable
data class EnergyLevelDto(
    val batteryPercent: VehicleValue<Float> = VehicleValue(value = null, status = false),
    val distanceDisplayUnit: VehicleValue<UnitType> = VehicleValue(
        value = UnitType.UNKNOWN,
        status = false
    ),
    val energyIsLow: VehicleValue<Boolean> = VehicleValue(value = null, status = false),
    val fuelPercent: VehicleValue<Float> = VehicleValue(value = null, status = false),
    val fuelVolumeDisplayUnit: VehicleValue<UnitType> = VehicleValue(
        value = UnitType.UNKNOWN,
        status = false
    ),
    val rangeRemainingMeters: VehicleValue<Float> = VehicleValue(value = null, status = false),
    val rawString: String = ""
)

@Immutable
@Serializable
data class SpeedDto(
    val displaySpeedMetersPerSecond: VehicleValue<Float> = VehicleValue(value = null, status = false),
    val rawSpeedMetersPerSecond: VehicleValue<Float> = VehicleValue(value = null, status = false),
    val speedDisplayUnit: VehicleValue<UnitType> = VehicleValue(
        value = UnitType.UNKNOWN,
        status = false
    ),
    val rawString: String = ""
)

@Immutable
@Serializable
data class MileageDto(
    val distanceDisplayUnit: VehicleValue<UnitType> = VehicleValue(
        value = UnitType.UNKNOWN,
        status = false
    ),
    val odometerMeters: VehicleValue<Float> = VehicleValue(value = null, status = false),
    val rawString: String = ""
)

@Immutable
@Serializable
data class EvStatusDto(
    val evChargePortOpen: VehicleValue<Boolean> = VehicleValue(value = null, status = false),
    val evChargePortConnected: VehicleValue<Boolean> = VehicleValue(value = null, status = false),
    val rawString: String = ""
)

@Immutable
@Serializable
data class TollCardDto(
    val cardState: VehicleValue<Int> = VehicleValue(value = null, status = false),
    val rawString: String = ""
)

@Immutable
@Serializable
data class AccelerometerDto(
    val forces: VehicleValue<List<Float>> = VehicleValue(value = null, status = false),
    val rawString: String = ""
)

@Immutable
@Serializable
data class CompassDto(
    val orientations: VehicleValue<List<Float>> = VehicleValue(value = null, status = false),
    val rawString: String = ""
)

@Immutable
@Serializable
data class GyroscopeDto(
    val rotations: VehicleValue<List<Float>> = VehicleValue(value = null, status = false),
    val rawString: String = ""
)

@Immutable
@Serializable
data class HardwareLocationDto(
    val location: VehicleValue<@Contextual Location> = VehicleValue(value = null, status = false),
    val rawString: String = ""
)

@Immutable
@Serializable
data class ClimateDto(
    val hvacPowerState: VehicleValue<Boolean>? = null,
    val hvacAcState: VehicleValue<Boolean>? = null,
    val hvacMaxAcModeState: VehicleValue<Boolean>? = null,
    val hvacCabinTemperature: VehicleValue<Float>? = null,
    val fanSpeedLevel: VehicleValue<Int>? = null,
    val fanDirection: VehicleValue<Int>? = null,
    val seatTemperatureLevel: VehicleValue<Int>? = null,
    val seatVentilationLevel: VehicleValue<Int>? = null,
    val steeringWheelHeatState: VehicleValue<Boolean>? = null,
    val hvacRecirculationState: VehicleValue<Boolean>? = null,
    val hvacAutoRecirculationState: VehicleValue<Boolean>? = null,
    val hvacAutoModeState: VehicleValue<Boolean>? = null,
    val hvacDualModeState: VehicleValue<Boolean>? = null,
    val defrosterState: VehicleValue<Boolean>? = null,
    val maxDefrosterState: VehicleValue<Boolean>? = null,
    val electricDefrosterState: VehicleValue<Boolean>? = null,
    val rawString: String? = null,
)

@Immutable
@Serializable
data class VehicleValue<T>(
    val value: T? = null,
    val status: Boolean,
)