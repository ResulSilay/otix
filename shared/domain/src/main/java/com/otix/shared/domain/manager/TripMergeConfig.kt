package com.otix.shared.domain.manager

internal data class TripMergeConfig(
    val checkTimeGap: Boolean = true,
    val checkLocationChange: Boolean = true,
    val checkFuelDiff: Boolean = false,
    val checkBatteryDiff: Boolean = false,
    val maxTimeGapMs: Long = 5 * 60 * 1000L,
    val maxLocationDelta: Double = 0.0005,
    val maxFuelDiff: Double = 2.0,
    val maxBatteryDiff: Double = 2.0
)