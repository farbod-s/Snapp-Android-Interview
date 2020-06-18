package com.snapp.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.snapp.cache.dao.CachedVehicleDao
import com.snapp.cache.model.CachedVehicle
import javax.inject.Inject

@Database(entities = [CachedVehicle::class], version = 1)
abstract class SnappDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedVehicleDao(): CachedVehicleDao

    companion object {

        @Volatile
        private var INSTANCE: SnappDatabase? = null

        fun getInstance(context: Context): SnappDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SnappDatabase::class.java, Const.DATABASE_NAME
            )
                .build()
    }
}