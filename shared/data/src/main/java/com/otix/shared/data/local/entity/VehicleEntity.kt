package com.otix.shared.data.local.entity

import com.otix.shared.domain.type.UnitType
import kotlinx.serialization.Serializable

data class VehicleEntity(
    val id: String,
    val info: VehicleInfoEntity
)

@Serializable
data class VehicleInfoEntity(
    val model: ModelEntity = ModelEntity(),
    val energyProfile: EnergyProfileEntity = EnergyProfileEntity(),
    val energyLevel: EnergyLevelEntity = EnergyLevelEntity(),
    val speed: SpeedEntity = SpeedEntity(),
    val mileage: MileageEntity = MileageEntity(),
    val evStatus: EvStatusEntity = EvStatusEntity(),
    val tollCard: TollCardEntity = TollCardEntity(),
    val accelerometer: AccelerometerEntity = AccelerometerEntity()
)

@Serializable
data class ModelEntity(
    val manufacturer: VehicleEntityValue<String> = VehicleEntityValue(value = "-", status = false),
    val name: VehicleEntityValue<String> = VehicleEntityValue(value = "-", status = false),
    val year: VehicleEntityValue<Int> = VehicleEntityValue(value = -1, status = false),
)

@Serializable
data class EnergyProfileEntity(
    val fuelTypes: VehicleEntityValue<List<Int>> = VehicleEntityValue(value = emptyList(), status = false),
    val evConnectorTypes: VehicleEntityValue<List<Int>> = VehicleEntityValue(value = emptyList(), status = false)
)

@Serializable
data class EnergyLevelEntity(
    val batteryPercent: VehicleEntityValue<Float> = VehicleEntityValue(value = -1F, status = false),
    val distanceDisplayUnit: VehicleEntityValue<UnitType> = VehicleEntityValue(
        value = UnitType.UNKNOWN,
        status = false
    ),
    val energyIsLow: VehicleEntityValue<Boolean> = VehicleEntityValue(value = false, status = false),
    val fuelPercent: VehicleEntityValue<Float> = VehicleEntityValue(value = -1F, status = false),
    val fuelVolumeDisplayUnit: VehicleEntityValue<UnitType> = VehicleEntityValue(
        value = UnitType.UNKNOWN,
        status = false
    ),
    val rangeRemainingMeters: VehicleEntityValue<Float> = VehicleEntityValue(value = -1F, status = false),
)

@Serializable
data class SpeedEntity(
    val displaySpeedMetersPerSecond: VehicleEntityValue<Float> = VehicleEntityValue(value = -1F, status = false),
    val rawSpeedMetersPerSecond: VehicleEntityValue<Float> = VehicleEntityValue(value = -1F, status = false),
    val speedDisplayUnit: VehicleEntityValue<UnitType> = VehicleEntityValue(
        value = UnitType.UNKNOWN,
        status = false
    ),
)

@Serializable
data class MileageEntity(
    val distanceDisplayUnit: VehicleEntityValue<UnitType> = VehicleEntityValue(
        value = UnitType.UNKNOWN,
        status = false
    ),
    val odometerMeters: VehicleEntityValue<Float> = VehicleEntityValue(value = -1F, status = false),
)

@Serializable
data class EvStatusEntity(
    val evChargePortOpen: VehicleEntityValue<Boolean> = VehicleEntityValue(value = false, status = false),
    val evChargePortConnected: VehicleEntityValue<Boolean> = VehicleEntityValue(value = false, status = false),
)

@Serializable
data class TollCardEntity(
    val cardState: VehicleEntityValue<Int> = VehicleEntityValue(value = -1, status = false),
)

@Serializable
data class AccelerometerEntity(
    val forces: VehicleEntityValue<List<Float>> = VehicleEntityValue(value = emptyList(), status = false),
)

@Serializable
data class VehicleEntityValue<T>(
    val value: T? = null,
    val status: Boolean
)