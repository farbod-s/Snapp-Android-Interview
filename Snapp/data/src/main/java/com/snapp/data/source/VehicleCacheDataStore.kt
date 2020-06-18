package com.snapp.data.source

import com.snapp.data.model.VehicleEntity
import com.snapp.data.repository.VehicleCache
import com.snapp.data.repository.VehicleDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [VehicleCache] interface to provide a means of communicating
 * with the local data source
 */
class VehicleCacheDataStore @Inject constructor(
    private val vehicleCache: VehicleCache
) :
    VehicleDataStore {

    /**
     * Clear all Vehicles from the cache
     */
    override fun clearVehicles(): Completable {
        return vehicleCache.clearVehicles()
    }

    /**
     * Save a given [List] of [VehicleEntity] instances to the cache
     */
    override fun saveVehicles(vehicles: List<VehicleEntity>): Completable {
        return vehicleCache.saveVehicles(vehicles)
            .doOnComplete {
                vehicleCache.setLastCacheTime(System.currentTimeMillis())
            }
    }

    /**
     * Retrieve a list of [VehicleEntity] instance from the cache
     */
    override fun getVehicles(): Flowable<List<VehicleEntity>> {
        return vehicleCache.getVehicles()
    }

    /**
     * Retrieve a list of [VehicleEntity] instance from the cache
     */
    override fun isCached(): Single<Boolean> {
        return vehicleCache.isCached()
    }
}