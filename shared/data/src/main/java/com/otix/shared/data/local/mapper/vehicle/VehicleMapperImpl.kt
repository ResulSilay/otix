package com.otix.shared.data.local.mapper.vehicle

import com.otix.shared.data.local.entity.AccelerometerEntity
import com.otix.shared.data.local.entity.EnergyLevelEntity
import com.otix.shared.data.local.entity.EnergyProfileEntity
import com.otix.shared.data.local.entity.EvStatusEntity
import com.otix.shared.data.local.entity.MileageEntity
import com.otix.shared.data.local.entity.ModelEntity
import com.otix.shared.data.local.entity.SpeedEntity
import com.otix.shared.data.local.entity.TollCardEntity
import com.otix.shared.data.local.entity.VehicleEntity
import com.otix.shared.data.local.entity.VehicleEntityValue
import com.otix.shared.data.local.entity.VehicleInfoEntity
import com.otix.shared.domain.model.AccelerometerDto
import com.otix.shared.domain.model.CompassDto
import com.otix.shared.domain.model.EnergyLevelDto
import com.otix.shared.domain.model.EnergyProfileDto
import com.otix.shared.domain.model.EvStatusDto
import com.otix.shared.domain.model.GyroscopeDto
import com.otix.shared.domain.model.HardwareLocationDto
import com.otix.shared.domain.model.MileageDto
import com.otix.shared.domain.model.ModelDto
import com.otix.shared.domain.model.SpeedDto
import com.otix.shared.domain.model.TollCardDto
import com.otix.shared.domain.model.Vehicle
import com.otix.shared.domain.model.VehicleInfo
import com.otix.shared.domain.model.VehicleValue
import com.otix.shared.domain.type.VehicleState

internal class VehicleMapperImpl : VehicleMapper {

    override fun toVehicleEntity(vehicle: Vehicle): VehicleEntity = VehicleEntity(
        id = vehicle.id,
        info = VehicleInfoEntity(
            model = ModelEntity(
                manufacturer = domainToEntity(vehicle.info.model.manufacturer),
                name = domainToEntity(vehicle.info.model.name),
                year = domainToEntity(vehicle.info.model.year),
            ),
            energyProfile = EnergyProfileEntity(
                fuelTypes = domainToEntity(vehicle.info.energyProfile.fuelTypes),
                evConnectorTypes = domainToEntity(vehicle.info.energyProfile.evConnectorTypes)
            ),
            energyLevel = EnergyLevelEntity(
                batteryPercent = domainToEntity(vehicle.info.energyLevel.batteryPercent),
                distanceDisplayUnit = domainToEntity(vehicle.info.energyLevel.distanceDisplayUnit),
                energyIsLow = domainToEntity(vehicle.info.energyLevel.energyIsLow),
                fuelPercent = domainToEntity(vehicle.info.energyLevel.fuelPercent),
                fuelVolumeDisplayUnit = domainToEntity(vehicle.info.energyLevel.fuelVolumeDisplayUnit),
                rangeRemainingMeters = domainToEntity(vehicle.info.energyLevel.rangeRemainingMeters),
            ),
            speed = SpeedEntity(
                displaySpeedMetersPerSecond = domainToEntity(vehicle.info.speed.displaySpeedMetersPerSecond),
                rawSpeedMetersPerSecond = domainToEntity(vehicle.info.speed.rawSpeedMetersPerSecond),
                speedDisplayUnit = domainToEntity(vehicle.info.speed.speedDisplayUnit),
            ),
            mileage = MileageEntity(
                distanceDisplayUnit = domainToEntity(vehicle.info.mileage.distanceDisplayUnit),
                odometerMeters = domainToEntity(vehicle.info.mileage.odometerMeters)
            ),
            evStatus = EvStatusEntity(
                evChargePortOpen = domainToEntity(vehicle.info.evStatus.evChargePortOpen),
                evChargePortConnected = domainToEntity(vehicle.info.evStatus.evChargePortConnected),
            ),
            tollCard = TollCardEntity(
                cardState = domainToEntity(vehicle.info.tollCard.cardState)
            ),
            accelerometer = AccelerometerEntity(
                forces = domainToEntity(vehicle.info.accelerometer.forces)
            )
        )
    )

    override fun fromVehicleEntity(entity: VehicleEntity): Vehicle = Vehicle(
        id = entity.id,
        info = VehicleInfo(
            model = ModelDto(
                manufacturer = entityToDomain(entity.info.model.manufacturer),
                name = entityToDomain(entity.info.model.name),
                year = entityToDomain(entity.info.model.year)
            ),
            energyProfile = EnergyProfileDto(
                fuelTypes = entityToDomain(entity.info.energyProfile.fuelTypes),
                evConnectorTypes = entityToDomain(entity.info.energyProfile.evConnectorTypes)
            ),
            energyLevel = EnergyLevelDto(
                batteryPercent = entityToDomain(entity.info.energyLevel.batteryPercent),
                distanceDisplayUnit = entityToDomain(entity.info.energyLevel.distanceDisplayUnit),
                energyIsLow = entityToDomain(entity.info.energyLevel.energyIsLow),
                fuelPercent = entityToDomain(entity.info.energyLevel.fuelPercent),
                fuelVolumeDisplayUnit = entityToDomain(entity.info.energyLevel.fuelVolumeDisplayUnit),
                rangeRemainingMeters = entityToDomain(entity.info.energyLevel.rangeRemainingMeters)
            ),
            speed = SpeedDto(
                displaySpeedMetersPerSecond = entityToDomain(entity.info.speed.displaySpeedMetersPerSecond),
                rawSpeedMetersPerSecond = entityToDomain(entity.info.speed.rawSpeedMetersPerSecond),
                speedDisplayUnit = entityToDomain(entity.info.speed.speedDisplayUnit)
            ),
            mileage = MileageDto(
                distanceDisplayUnit = entityToDomain(entity.info.mileage.distanceDisplayUnit),
                odometerMeters = entityToDomain(entity.info.mileage.odometerMeters)
            ),
            evStatus = EvStatusDto(
                evChargePortOpen = entityToDomain(entity.info.evStatus.evChargePortOpen),
                evChargePortConnected = entityToDomain(entity.info.evStatus.evChargePortConnected)
            ),
            tollCard = TollCardDto(
                cardState = entityToDomain(entity.info.tollCard.cardState)
            ),
            accelerometer = AccelerometerDto(
                forces = entityToDomain(entity.info.accelerometer.forces)
            ),
            gyroscope = GyroscopeDto(),
            compass = CompassDto(),
            hardwareLocation = HardwareLocationDto(),
            state = VehicleState.IDLE
        )
    )

    private fun <T> domainToEntity(value: VehicleValue<T>): VehicleEntityValue<T> = VehicleEntityValue(
        value = value.value,
        status = value.status
    )

    private fun <T> entityToDomain(value: VehicleEntityValue<T>): VehicleValue<T> = VehicleValue(
        value = value.value,
        status = value.status
    )
}