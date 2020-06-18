package com.snapp.presentation

/**
 * Representation for a [VehicleView] instance for this layers Model representation
 */
class VehicleView(
    val id: Int,
    val type: String,
    val lat: Double,
    val lng: Double,
    val bearing: Int,
    val imageUrl: String
)