package com.otix.shared.data.local.mapper.trip

import com.otix.core.extension.orZero
import com.otix.shared.data.local.entity.BatteryPercentItem
import com.otix.shared.data.local.entity.FuelPercentItem
import com.otix.shared.data.local.entity.GeoLocationItem
import com.otix.shared.data.local.entity.TripDataEntity
import com.otix.shared.data.local.entity.TripEntity
import com.otix.shared.domain.model.BatteryPercent
import com.otix.shared.domain.model.FuelPercent
import com.otix.shared.domain.model.GeoLocation
import com.otix.shared.domain.model.Trip
import com.otix.shared.domain.model.TripInfo

class TripMapperImpl : TripMapper {

    override fun toTripEntity(trip: Trip): TripEntity = TripEntity(
        id = trip.id,
        vehicleId = trip.vehicleId,
        data = TripDataEntity(
            currentLocation = trip.info.currentLocation.toGeoLocationItem(),
            routePoints = trip.info.routePoints.map { it.toGeoLocationItem() },
            fuelStartPercent = trip.info.startFuelPercent,
            fuelEndPercent = trip.info.endFuelPercent,
            fuelVolumeLiters = trip.info.fuelVolumeLiters.toFloat(),
            fuelUsedLiters = trip.info.fuelUsedLiters,
            fuelPercentages = trip.info.fuelPercentages.map { it.toFuelPercentItem() },
            batteryStartPercent = trip.info.startBatteryPercent,
            batteryEndPercent = trip.info.endBatteryPercent,
            batteryCapacityKWh = trip.info.batteryCapacityKWh.toFloat(),
            batteryUsedKWh = trip.info.batteryUsedKWh,
            batteryPercentages = trip.info.batteryPercentages.map { it.toBatteryPercentItem() },
            distanceMeters = trip.info.distanceMeters,
            startDateTime = trip.info.startTime,
            endDateTime = trip.info.endTime
        )
    )

    override fun fromTripEntity(entity: TripEntity): Trip = Trip(
        id = entity.id,
        vehicleId = entity.vehicleId,
        info = entity.data.let { data ->
            TripInfo(
                currentLocation = data.currentLocation?.toGeoLocation() ?: GeoLocation(),
                routePoints = data.routePoints?.map { it.toGeoLocation() }.orEmpty(),
                startTime = data.startDateTime ?: 0L,
                endTime = data.endDateTime ?: 0L,
                startFuelPercent = data.fuelStartPercent ?: -1F,
                endFuelPercent = data.fuelEndPercent ?: -1F,
                fuelVolumeLiters = data.fuelVolumeLiters?.toInt().orZero(),
                fuelPercentages = data.fuelPercentages?.map { it.toFuelPercent() }.orEmpty(),
                startBatteryPercent = data.batteryStartPercent ?: -1F,
                endBatteryPercent = data.batteryEndPercent ?: -1F,
                batteryCapacityKWh = data.batteryCapacityKWh?.toInt().orZero(),
                batteryPercentages = data.batteryPercentages?.map { percent ->
                    percent.toBatteryPercent()
                }.orEmpty()
            )
        }
    )

    private fun GeoLocation.toGeoLocationItem(): GeoLocationItem =
        GeoLocationItem(
            address = address,
            latitude = latitude,
            longitude = longitude,
            timestamp = timestamp
        )

    private fun GeoLocationItem.toGeoLocation(): GeoLocation =
        GeoLocation(
            address = address.orEmpty(),
            latitude = latitude.orZero(),
            longitude = longitude.orZero()
        )

    private fun FuelPercent.toFuelPercentItem(): FuelPercentItem =
        FuelPercentItem(
            value = value,
            timestamp = timestamp
        )

    private fun FuelPercentItem.toFuelPercent(): FuelPercent =
        FuelPercent(
            value = value.orZero(),
            timestamp = timestamp.orZero()
        )

    private fun BatteryPercent.toBatteryPercentItem(): BatteryPercentItem =
        BatteryPercentItem(
            value = value,
            timestamp = timestamp
        )

    private fun BatteryPercentItem.toBatteryPercent(): BatteryPercent =
        BatteryPercent(
            value = value.orZero(),
            timestamp = timestamp.orZero()
        )
}