package com.otix.core.navigation.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class TripDetail(
    val tripId: Long,
    val isDemoAccount: Boolean,
) : NavKey
