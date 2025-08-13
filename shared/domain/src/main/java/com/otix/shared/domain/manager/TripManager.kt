package com.otix.shared.domain.manager

import com.otix.shared.domain.model.Trip

interface TripManager {

    fun handleIncomingTrip(newTrip: Trip): Trip?

    fun getCurrentTrip(): Trip?

    fun reset()
}
