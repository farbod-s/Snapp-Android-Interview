package com.snapp.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.snapp.cache.db.Const
import com.snapp.cache.model.CachedVehicle

@Dao
abstract class CachedVehicleDao {

    @Query(Const.QUERY_ALL_VEHICLES)
    abstract fun getVehicles(): List<CachedVehicle>

    @Query(Const.DELETE_ALL_VEHICLES)
    abstract fun clearVehicles()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertVehicle(cachedVehicle: CachedVehicle)
}