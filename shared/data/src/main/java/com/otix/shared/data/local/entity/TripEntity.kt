package com.otix.shared.data.local.entity

import kotlinx.serialization.Serializable

data class TripEntity(
    val id: Long?,
    val vehicleId: String,
    val data: TripDataEntity,
)

@Serializable
data class TripDataEntity(
    val currentLocation: GeoLocationItem? = null,
    val fuelStartPercent: Float? = null,
    val fuelEndPercent: Float? = null,
    val fuelVolumeLiters: Float? = null,
    val fuelUsedLiters: Float? = null,
    val fuelPercentages: List<FuelPercentItem>? = null,
    val batteryStartPercent: Float? = null,
    val batteryEndPercent: Float? = null,
    val batteryCapacityKWh: Float? = null,
    val batteryUsedKWh: Float? = null,
    val batteryPercentages: List<BatteryPercentItem>? = null,
    val distanceMeters: Float? = null,
    val startDateTime: Long? = null,
    val endDateTime: Long? = null,
    val routePoints: List<GeoLocationItem>? = null
)

@Serializable
data class GeoLocationItem(
    val address: String? = null,
    val latitude: Double? = 0.0,
    val longitude: Double? = 0.0,
    val timestamp: Long? = 0L
)

@Serializable
data class FuelPercentItem(
    val value: Float? = null,
    val timestamp: Long? = null
)

@Serializable
data class BatteryPercentItem(
    val value: Float? = null,
    val timestamp: Long? = null
)