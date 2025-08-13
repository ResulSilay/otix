package com.otix.shared.data.local.mapper.trip

import com.otix.shared.data.local.entity.TripEntity
import com.otix.shared.domain.model.Trip

interface TripMapper {

    fun toTripEntity(trip: Trip): TripEntity

    fun fromTripEntity(entity: TripEntity): Trip
}