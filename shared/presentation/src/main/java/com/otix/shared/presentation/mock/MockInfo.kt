package com.otix.shared.presentation.mock

import android.location.Location
import com.otix.shared.domain.model.AccelerometerDto
import com.otix.shared.domain.model.BatteryPercent
import com.otix.shared.domain.model.CompassDto
import com.otix.shared.domain.model.EnergyLevelDto
import com.otix.shared.domain.model.EnergyProfileDto
import com.otix.shared.domain.model.EvStatusDto
import com.otix.shared.domain.model.FuelPercent
import com.otix.shared.domain.model.GeoLocation
import com.otix.shared.domain.model.GyroscopeDto
import com.otix.shared.domain.model.HardwareLocationDto
import com.otix.shared.domain.model.MileageDto
import com.otix.shared.domain.model.ModelDto
import com.otix.shared.domain.model.PhoneInfo
import com.otix.shared.domain.model.SpeedDto
import com.otix.shared.domain.model.TollCardDto
import com.otix.shared.domain.model.Trip
import com.otix.shared.domain.model.TripInfo
import com.otix.shared.domain.model.VehicleInfo
import com.otix.shared.domain.model.VehicleValue
import com.otix.shared.domain.type.EvConnectorType
import com.otix.shared.domain.type.UnitType
import kotlinx.collections.immutable.persistentListOf

fun getPhoneInfoMock(): PhoneInfo = PhoneInfo(
    location = Location("PhoneProvider").apply {
        latitude = 34.035126
        longitude = -118.698837
        accuracy = 5F
        time = System.currentTimeMillis()
    }
)

fun getVehicleInfoMock(): VehicleInfo =
    VehicleInfo(
        vehicleId = "DEMO01",
        model = ModelDto(
            manufacturer = VehicleValue(value = "Demo Model", status = true),
            name = VehicleValue(value = "Demo Name", status = true),
            year = VehicleValue(value = 2025, status = true),
        ),
        speed = SpeedDto(
            displaySpeedMetersPerSecond = VehicleValue(
                value = 75.0F,
                status = true
            ),
            rawSpeedMetersPerSecond = VehicleValue(
                value = 75.000F,
                status = true
            ),
            speedDisplayUnit = VehicleValue(
                value = UnitType.METER,
                status = true
            ),
        ),
        energyProfile = EnergyProfileDto(
            fuelTypes = VehicleValue(
                value = persistentListOf(1),
                status = true
            ),
            evConnectorTypes = VehicleValue(
                value = persistentListOf(EvConnectorType.OTHER.code),
                status = true
            )
        ),
        energyLevel = EnergyLevelDto(
            batteryPercent = VehicleValue(value = 75.8F, status = true),
            distanceDisplayUnit = VehicleValue(
                value = UnitType.KILOMETER,
                status = true
            ),
            energyIsLow = VehicleValue(value = false, status = true),
            fuelPercent = VehicleValue(value = 47.8F, status = true),
            fuelVolumeDisplayUnit = VehicleValue(
                value = UnitType.LITER,
                status = true
            ),
            rangeRemainingMeters = VehicleValue(
                value = 265_000.0F,
                status = true
            )
        ),
        mileage = MileageDto(
            distanceDisplayUnit = VehicleValue(
                value = UnitType.MILE,
                status = true
            ),
            odometerMeters = VehicleValue(value = 86.0F, status = true)
        ),
        accelerometer = AccelerometerDto(
            forces = VehicleValue(
                value = persistentListOf(23.0F, 12.2F, -13.5F),
                status = true
            )
        ),
        compass = CompassDto(
            orientations = VehicleValue(
                value = persistentListOf(45F, 12.3F, -20.4F),
                status = true
            )
        ),
        gyroscope = GyroscopeDto(
            rotations = VehicleValue(
                value = persistentListOf(45F, 12.3F, -20.4F),
                status = true
            )
        ),
        evStatus = EvStatusDto(
            evChargePortConnected = VehicleValue(
                value = false,
                status = true
            ),
            evChargePortOpen = VehicleValue(
                value = true,
                status = true
            )
        ),
        hardwareLocation = HardwareLocationDto(
            location = VehicleValue(
                value = Location("HardwareProvider").apply {
                    latitude = 34.035126
                    longitude = -118.698890
                    accuracy = 5F
                    time = System.currentTimeMillis()
                },
                status = true
            )
        ),
        tollCard = TollCardDto(
            cardState = VehicleValue(
                value = 1,
                status = true
            )
        )
    )

fun getTripsMock() = listOf(
    Trip(
        id = 1,
        vehicleId = "1",
        info = TripInfo(
            currentLocation = GeoLocation(),
            startTime = 1_695_000_000_000L,
            endTime = 1_695_000_300_000L,
            startFuelPercent = 100F,
            endFuelPercent = 90F,
            startBatteryPercent = 100F,
            endBatteryPercent = 91.5F,
            batteryCapacityKWh = 35,
            fuelVolumeLiters = 50,
            routePoints = listOf(
                GeoLocation(
                    address = "Söğütözü, 06510 Çankaya/Ankara",
                    latitude = 39.909384,
                    longitude = 32.798935,
                    timestamp = 1_695_000_000_000L
                ),
                GeoLocation(
                    address = "Kızılırmak, 06510 Çankaya/Ankara",
                    latitude = 39.909160,
                    longitude = 32.795789,
                    timestamp = 1_695_000_060_000L
                ),
                GeoLocation(
                    address = "Üniversiteler, 06510 Çankaya/Ankara",
                    latitude = 39.908298,
                    longitude = 32.774743,
                    timestamp = 1_695_000_120_000L
                ),
                GeoLocation(
                    address = "Üniversiteler, 06790 Çankaya/Ankara",
                    latitude = 39.906445,
                    longitude = 32.727795,
                    timestamp = 1_695_000_180_000L
                ),
                GeoLocation(
                    address = "Erler, 06790 Etimesgut/Ankara",
                    latitude = 39.905609,
                    longitude = 32.706923,
                    timestamp = 1_695_000_240_000L
                ),
                GeoLocation(
                    address = "Ümit, 06790 Yenimahalle/Ankara",
                    latitude = 39.905275,
                    longitude = 32.698826,
                    timestamp = 1_695_000_300_000L
                ),
                GeoLocation(
                    address = "Ümit, 06790 Yenimahalle/Ankara",
                    latitude = 39.904921,
                    longitude = 32.696487,
                    timestamp = 1_695_000_360_000L
                ),
                GeoLocation(
                    address = "Ümit, 06810 Yenimahalle/Ankara",
                    latitude = 39.903078,
                    longitude = 32.692384,
                    timestamp = 1_695_000_430_000L
                ),
                GeoLocation(
                    address = "Erler, 06790 Yenimahalle/Ankara",
                    latitude = 39.900831,
                    longitude = 32.688756,
                    timestamp = 1_695_000_470_000L
                )
            ),
            fuelPercentages = listOf(
                FuelPercent(value = 100F, timestamp = 1_695_000_000_000L),
                FuelPercent(value = 98F, timestamp = 1_695_000_030_000L),
                FuelPercent(value = 80F, timestamp = 1_695_000_060_000L),
                FuelPercent(value = 74F, timestamp = 1_695_000_090_000L),
                FuelPercent(value = 62F, timestamp = 1_695_000_120_000L),
                FuelPercent(value = 50F, timestamp = 1_695_000_300_000L),
                FuelPercent(value = 46F, timestamp = 1_695_000_420_000L),
                FuelPercent(value = 35F, timestamp = 1_695_000_580_000L),
                FuelPercent(value = 20F, timestamp = 1_695_000_720_000L)
            ),
            batteryPercentages = listOf(
                BatteryPercent(value = 100F, timestamp = 1_695_000_000_000L),
                BatteryPercent(value = 98F, timestamp = 1_695_000_030_000L),
                BatteryPercent(value = 96.5F, timestamp = 1_695_000_060_000L),
                BatteryPercent(value = 95F, timestamp = 1_695_000_090_000L),
                BatteryPercent(value = 93F, timestamp = 1_695_000_120_000L),
                BatteryPercent(value = 91.5F, timestamp = 1_695_000_300_000L),
                BatteryPercent(value = 90.5F, timestamp = 1_695_000_420_000L),
                BatteryPercent(value = 88.5F, timestamp = 1_695_000_580_000L),
                BatteryPercent(value = 86.5F, timestamp = 1_695_000_680_000L)
            )
        )
    )
)