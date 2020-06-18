package com.snapp.remote.service

import com.snapp.remote.model.VehiclesResponse
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Defines the abstract methods used for interacting with the Snapp API
 */
interface SnappService {

    @GET("document.json")
    fun getVehicles(): Flowable<VehiclesResponse>
}