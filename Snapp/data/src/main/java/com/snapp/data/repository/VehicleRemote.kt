package com.snapp.data.repository

import com.snapp.data.model.VehicleEntity
import io.reactivex.Flowable

/**
 * Interface defining methods for the caching of Vehicles. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface VehicleRemote {

    /**
     * Retrieve a list of Vehicles, from the cache
     */
    fun getVehicles(): Flowable<List<VehicleEntity>>
}