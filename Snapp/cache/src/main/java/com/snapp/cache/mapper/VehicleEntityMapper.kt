package com.snapp.cache.mapper

import com.snapp.cache.model.CachedVehicle
import com.snapp.data.model.VehicleEntity
import javax.inject.Inject

/**
 * Map a [CachedVehicle] instance to and from a [VehicleEntity] instance when data is moving between
 * this later and the Data layer
 */
class VehicleEntityMapper @Inject constructor() :
    EntityMapper<CachedVehicle, VehicleEntity> {

    /**
     * Map a [VehicleEntity] instance to a [CachedVehicle] instance
     */
    override fun mapToCached(type: VehicleEntity): CachedVehicle {
        return CachedVehicle(
            id = type.id,
            type = type.type,
            lat = type.lat,
            lng = type.lng,
            bearing = type.bearing,
            imageUrl = type.imageUrl
        )
    }

    /**
     * Map a [CachedVehicle] instance to a [VehicleEntity] instance
     */
    override fun mapFromCached(type: CachedVehicle): VehicleEntity {
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