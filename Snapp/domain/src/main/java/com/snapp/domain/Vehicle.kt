package com.snapp.domain

/**
 * Representation for a [Vehicle] fetched from an external layer data source
 */
data class Vehicle(
    val id: Int,
    val type: String,
    val lat: Double,
    val lng: Double,
    val bearing: Int,
    val imageUrl: String
)