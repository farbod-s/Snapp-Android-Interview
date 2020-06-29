package com.snapp.data.source

import com.snapp.data.model.VehicleEntity
import com.snapp.data.repository.VehicleDataStore
import com.snapp.data.repository.VehicleRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [VehicleRemote] interface to provide a means of communicating
 * with the remote data source
 */
open class VehicleRemoteDataStore @Inject constructor(
    private val vehicleRemote: VehicleRemote
) :
    VehicleDataStore {

    override fun clearVehicles(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveVehicles(vehicles: List<VehicleEntity>): Completable {
        throw UnsupportedOperationException()
    }

    /**
     * Retrieve a list of [VehicleEntity] instances from the API
     */
    override fun getVehicles(): Flowable<List<VehicleEntity>> {
        return vehicleRemote.getVehicles()
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }
}