package com.snapp.data.mapper

import com.snapp.data.model.VehicleEntity
import com.snapp.domain.model.Vehicle
import javax.inject.Inject

/**
 * Map a [VehicleEntity] to and from a [Vehicle] instance when data is moving between
 * this later and the Domain layer
 */
class VehicleMapper @Inject constructor() : Mapper<VehicleEntity, Vehicle> {

    /**
     * Map a [VehicleEntity] instance to a [Vehicle] instance
     */
    override fun mapFromEntity(type: VehicleEntity): Vehicle {
        return Vehicle(
            id = type.id,
            type = type.type,
            lat = type.lat,
            lng = type.lng,
            bearing = type.bearing,
            imageUrl = type.imageUrl
        )
    }

    /**
     * Map a [Vehicle] instance to a [VehicleEntity] instance
     */
    override fun mapToEntity(type: Vehicle): VehicleEntity {
        return VehicleEntity(
            id = type.id,
            type = type.type,
            lat = type.lat,
            lng = type.lng,
            bearing = type.bearing,
            imageUrl = type.imageUrl
        )
    }
}