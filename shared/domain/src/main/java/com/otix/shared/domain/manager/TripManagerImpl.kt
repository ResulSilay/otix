package com.otix.shared.domain.manager

import com.otix.shared.domain.model.BatteryPercent
import com.otix.shared.domain.model.FuelPercent
import com.otix.shared.domain.model.GeoLocation
import com.otix.shared.domain.model.Trip
import kotlin.math.abs

internal class TripManagerImpl(
    private val config: TripMergeConfig = TripMergeConfig()
) : TripManager {

    private var currentTrip: Trip? = null

    override fun handleIncomingTrip(newTrip: Trip): Trip? =
        if (isSameTrip(previous = currentTrip, current = newTrip)) {
            currentTrip = currentTrip?.let { mergeTrips(it, newTrip) }
            null
        } else {
            val finishedTrip = currentTrip
            currentTrip = newTrip
            finishedTrip
        }

    private fun isSameTrip(previous: Trip?, current: Trip): Boolean {
        if (previous == null) return false

        val checks = mutableListOf<Boolean>()

        if (config.checkTimeGap) {
            val timeDiff = current.info.startTime - previous.info.endTime
            checks += timeDiff in 0..config.maxTimeGapMs
        }

        if (config.checkLocationChange) {
            val hasMoved = hasLocationChanged(
                previous.info.currentLocation,
                current.info.currentLocation
            )
            checks += !hasMoved
        }

        if (config.checkFuelDiff) {
            val fuelDiff = abs(x = current.info.startFuelPercent - previous.info.endFuelPercent)
            checks += fuelDiff <= config.maxFuelDiff
        }

        if (config.checkBatteryDiff) {
            val batteryDiff = abs(x = current.info.startBatteryPercent - previous.info.endBatteryPercent)
            checks += batteryDiff <= config.maxBatteryDiff
        }

        return checks.all { it }
    }

    private fun hasLocationChanged(from: GeoLocation, to: GeoLocation): Boolean {
        val latDiff = abs(x = from.latitude - to.latitude)
        val lngDiff = abs(x = from.longitude - to.longitude)
        return latDiff > config.maxLocationDelta || lngDiff > config.maxLocationDelta
    }

    private fun mergeTrips(old: Trip, latest: Trip): Trip = old.copy(
        info = old.info.copy(
            endTime = latest.info.endTime,
            endFuelPercent = latest.info.endFuelPercent,
            endBatteryPercent = latest.info.endBatteryPercent,
            fuelPercentages = old.info.fuelPercentages + FuelPercent(
                value = latest.info.endFuelPercent,
                timestamp = latest.info.endTime
            ),
            batteryPercentages = old.info.batteryPercentages + BatteryPercent(
                value = latest.info.endBatteryPercent,
                timestamp = latest.info.endTime
            ),
            routePoints = old.info.routePoints + latest.info.routePoints
        )
    )

    override fun getCurrentTrip(): Trip? = currentTrip

    override fun reset() {
        currentTrip = null
    }
}