package com.otix.shared.domain.model

data class GeoLocation(
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val timestamp: Long = 0L
)