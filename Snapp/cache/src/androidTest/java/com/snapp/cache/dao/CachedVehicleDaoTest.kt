package com.snapp.cache.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.snapp.cache.db.SnappDatabase
import com.snapp.cache.factory.VehicleFactory
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class CachedVehicleDaoTest {

    private lateinit var snappDatabase: SnappDatabase

    @Before
    fun initDb() {
        // Using an in-memory database so that the information stored here disappears when the
        // process is killed
        snappDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SnappDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDb() {
        // Clear all data to avoid test pollution
        snappDatabase.apply {
            clearAllTables()
            close()
        }
    }

    @Test
    fun insertVehiclesSavesData() {
        val cachedVehicle = VehicleFactory.generateCachedVehicle()
        snappDatabase.cachedVehicleDao().insertVehicle(cachedVehicle)

        val vehicles = snappDatabase.cachedVehicleDao().getVehicles()

        assert(vehicles.isNotEmpty())
    }

    @Test
    fun getVehiclesRetrievesData() {
        val cachedVehicles = VehicleFactory.generateCachedVehicles(3)
        cachedVehicles.forEach {
            snappDatabase.cachedVehicleDao().insertVehicle(it)
        }

        val retrievedVehicles = snappDatabase.cachedVehicleDao().getVehicles()

        assert(retrievedVehicles == cachedVehicles.sortedWith(compareBy({ it.id }, { it.id })))
    }
}