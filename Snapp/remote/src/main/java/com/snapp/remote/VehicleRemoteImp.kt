package com.snapp.remote

import com.snapp.data.model.VehicleEntity
import com.snapp.data.repository.VehicleRemote
import com.snapp.remote.mapper.VehicleEntityMapper
import com.snapp.remote.service.SnappService
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Remote implementation for retrieving Vehicle instances. This class implements the
 * [VehicleRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class VehicleRemoteImp @Inject constructor(
    private val snappService: SnappService,
    private val entityMapper: VehicleEntityMapper
) :
    VehicleRemote {

    /**
     * Retrieve a list of [VehicleEntity] instances from the [SnappService].
     */
    override fun getVehicles(): Flowable<List<VehicleEntity>> {
        return snappService.getVehicles()
            .map { it.vehicles }
            .map {
                val entities = mutableListOf<VehicleEntity>()
                it.forEach { entities.add(entityMapper.mapFromRemote(it)) }
                entities
            }
    }
}