package com.snapp.presentation.mapper

import com.snapp.domain.model.Vehicle
import com.snapp.presentation.model.VehicleView
import javax.inject.Inject

/**
 * Map a [VehicleView] to and from a [Vehicle] instance when data is moving between
 * this layer and the Domain layer
 */
open class VehicleMapper @Inject constructor() : Mapper<VehicleView, Vehicle> {

    /**
     * Map a [Vehicle] instance to a [VehicleView] instance
     */
    override fun mapToView(type: Vehicle): VehicleView {
        return VehicleView(
            id = type.id,
            type = type.type,
            lat = type.lat,
            lng = type.lng,
            bearing = type.bearing,
            imageUrl = type.imageUrl
        )
    }
}