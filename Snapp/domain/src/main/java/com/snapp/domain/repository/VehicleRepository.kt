package com.snapp.domain.repository

import com.snapp.domain.model.Vehicle
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface VehicleRepository {

    fun clearVehicles(): Completable

    fun saveVehicles(vehicles: List<Vehicle>): Completable

    fun getVehicles(): Flowable<List<Vehicle>>
}