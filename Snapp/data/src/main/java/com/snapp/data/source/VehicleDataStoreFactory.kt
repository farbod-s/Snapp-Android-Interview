package com.snapp.data.source

import com.snapp.data.repository.VehicleCache
import com.snapp.data.repository.VehicleDataStore
import javax.inject.Inject

/**
 * Create an instance of a VehicleDataStore
 */
open class VehicleDataStoreFactory @Inject constructor(
    private val vehicleCache: VehicleCache,
    private val vehicleCacheDataStore: VehicleCacheDataStore,
    private val vehicleRemoteDataStore: VehicleRemoteDataStore
) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(isCached: Boolean): VehicleDataStore {
        if (isCached && !vehicleCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): VehicleDataStore {
        return vehicleCacheDataStore
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): VehicleDataStore {
        return vehicleRemoteDataStore
    }
}