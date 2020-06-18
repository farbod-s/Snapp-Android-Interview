package com.snapp.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Representation for a [VehicleModel] fetched from the API
 */
class VehicleModel(

    @SerializedName("type")
    val type: String,

    @SerializedName("lat")
    val lat: Double,

    @SerializedName("lng")
    val lng: Double,

    @SerializedName("bearing")
    val bearing: Int,

    @SerializedName("image_url")
    val imageUrl: String
)