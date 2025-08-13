package com.otix.shared.domain.model

import java.util.Locale

data class Trip(
    val id: Long? = null,
    val vehicleId: String,
    val info: TripInfo,
)

data class TripInfo(
    val currentLocation: GeoLocation,
    val startTime: Long,
    val endTime: Long,
    val startFuelPercent: Float,
    val endFuelPercent: Float,
    val fuelVolumeLiters: Int,
    val fuelPercentages: List<FuelPercent>,
    val startBatteryPercent: Float,
    val endBatteryPercent: Float,
    val batteryCapacityKWh: Int,
    val batteryPercentages: List<BatteryPercent>,
    val routePoints: List<GeoLocation>
) {
    val durationMillis: Long get() = endTime - startTime

    val distanceMeters: Float
        get() = String.format(
            locale = Locale.ROOT,
            format = "%.2f",
            args = arrayOf(calculateTotalDistance(routePoints) / 1000F)
        ).toFloat()

    val fuelUsedLiters: Float
        get() = ((startFuelPercent - endFuelPercent) / 100F) * fuelVolumeLiters

    val batteryUsedKWh: Float
        get() = ((startBatteryPercent - endBatteryPercent) / 100F) * batteryCapacityKWh
}

private fun calculateTotalDistance(locations: List<GeoLocation>): Float {
    if (locations.any { it.latitude == 0.0 || it.longitude == 0.0 }) {
        return 0F
    }

    var total = 0F
    val mLocations = locations.map { location ->
        val mLocation = android.location.Location("location")
        mLocation.latitude = location.latitude
        mLocation.longitude = location.longitude
        mLocation
    }

    for (i in 1 until mLocations.size) {
        total += mLocations[i - 1].distanceTo(mLocations[i])
    }

    return total
}
