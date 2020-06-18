package com.snapp.data

import com.snapp.data.mapper.VehicleMapper
import com.snapp.data.model.VehicleEntity
import com.snapp.data.source.VehicleDataStoreFactory
import com.snapp.domain.model.Vehicle
import com.snapp.domain.repository.VehicleRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Provides an implementation of the [VehicleRepository] interface for communicating to and from
 * data sources
 */
class VehicleDataRepository @Inject constructor(
    private val factory: VehicleDataStoreFactory,
    private val vehicleMapper: VehicleMapper
) :
    VehicleRepository {

    override fun clearVehicles(): Completable {
        return factory.retrieveCacheDataStore().clearVehicles()
    }

    override fun saveVehicles(vehicles: List<Vehicle>): Completable {
        val vehicleEntities = mutableListOf<VehicleEntity>()
        vehicles.map { vehicleEntities.add(vehicleMapper.mapToEntity(it)) }
        return factory.retrieveCacheDataStore().saveVehicles(vehicleEntities)
    }

    override fun getVehicles(): Flowable<List<Vehicle>> {
        return factory.retrieveCacheDataStore().isCached()
            .flatMapPublisher {
                factory.retrieveDataStore(it).getVehicles()
            }
            .flatMap {
                Flowable.just(it.map { vehicleMapper.mapFromEntity(it) })
            }
            .flatMap {
                saveVehicles(it).toSingle { it }.toFlowable()
            }
    }
}