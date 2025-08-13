package com.otix.core.navigation.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class FuelCapacity(
    val vehicleId: String,
    val isDemoAccount: Boolean,
) : NavKey