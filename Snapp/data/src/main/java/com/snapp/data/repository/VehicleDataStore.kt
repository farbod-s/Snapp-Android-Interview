package com.snapp.data.repository

import com.snapp.data.model.VehicleEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Interface defining methods for the data operations related to Vehicles.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface VehicleDataStore {

    fun clearVehicles(): Completable

    fun saveVehicles(vehicles: List<VehicleEntity>): Completable

    fun getVehicles(): Flowable<List<VehicleEntity>>

    fun isCached(): Single<Boolean>
}