package com.snapp.remote.mapper

import com.snapp.data.model.VehicleEntity
import com.snapp.remote.model.VehicleModel
import javax.inject.Inject

/**
 * Map a [VehicleModel] to and from a [VehicleEntity] instance when data is moving between
 * this later and the Data layer
 */
class VehicleEntityMapper @Inject constructor() : EntityMapper<VehicleModel, VehicleEntity> {

    private var vehicleId = 0

    /**
     * Map an instance of a [VehicleModel] to a [VehicleEntity] model
     */
    override fun mapFromRemote(type: VehicleModel): VehicleEntity {
        return VehicleEntity(
            id = ++vehicleId,
            type = type.type,
            lat = type.lat,
            lng = type.lng,
            bearing = type.bearing,
            imageUrl = type.imageUrl
        )
    }
}