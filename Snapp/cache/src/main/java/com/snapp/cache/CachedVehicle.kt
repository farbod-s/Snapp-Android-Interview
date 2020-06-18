package com.snapp.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.snapp.cache.db.Const

/**
 * Model used solely for the caching of a vehicle
 */
@Entity(tableName = Const.TABLE_NAME)
data class CachedVehicle(
    @PrimaryKey
    val id: Int,
    val type: String,
    val lat: Double,
    val lng: Double,
    val bearing: Int,
    val imageUrl: String
)