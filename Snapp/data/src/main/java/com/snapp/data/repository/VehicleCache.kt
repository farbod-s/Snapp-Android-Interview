package com.snapp.data.repository

import com.snapp.data.model.VehicleEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Vehicles. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface VehicleCache {

    /**
     * Clear all Vehicles from the cache.
     */
    fun clearVehicles(): Completable

    /**
     * Save a given list of Vehicle to the cache.
     */
    fun saveVehicles(vehicles: List<VehicleEntity>): Completable

    /**
     * Retrieve a list of Vehicles, from the cache.
     */
    fun getVehicles(): Flowable<List<VehicleEntity>>

    /**
     * Check whether there is a list of Vehicles stored in the cache.
     *
     * @return true if the list is cached, otherwise false
     */
    fun isCached(): Single<Boolean>

    /**
     * Set a point in time at when the cache was last updated.
     *
     * @param lastCache the point in time at when the cache was last updated
     */
    fun setLastCacheTime(lastCache: Long)

    /**
     * Check if the cache is expired.
     *
     * @return true if the cache is expired, otherwise false
     */
    fun isExpired(): Boolean
}