package com.snapp.data.model

/**
 * Representation for a [VehicleEntity] fetched from an external layer data source
 */
data class VehicleEntity(
    val id: Int,
    val type: String,
    val lat: Double,
    val lng: Double,
    val bearing: Int,
    val imageUrl: String
)