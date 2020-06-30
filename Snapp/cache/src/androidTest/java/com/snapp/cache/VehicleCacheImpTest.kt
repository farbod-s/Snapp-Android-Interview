package com.snapp.cache

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.snapp.cache.db.SnappDatabase
import com.snapp.cache.factory.VehicleFactory
import com.snapp.cache.mapper.VehicleEntityMapper
import com.snapp.cache.model.CachedVehicle
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VehicleCacheImpTest {

    private lateinit var snappDatabase: SnappDatabase

    private lateinit var entityMapper: VehicleEntityMapper
    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var vehicleCacheImp: VehicleCacheImp

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

        entityMapper = VehicleEntityMapper()
        preferencesHelper = PreferencesHelper(ApplicationProvider.getApplicationContext())
        vehicleCacheImp = VehicleCacheImp(
            snappDatabase,
            entityMapper,
            preferencesHelper
        )
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
    fun saveVehiclesSavesData() {
        val entities = VehicleFactory.generateVehicleEntityList(3)
        vehicleCacheImp.saveVehicles(entities).test()

        val numberOfRows = snappDatabase.cachedVehicleDao().getVehicles().size

        assertEquals(3, numberOfRows)
    }

    @Test
    fun getVehiclesReturnsData() {
        val vehicleEntities = VehicleFactory.generateVehicleEntityList(3)
        val cachedVehicles = mutableListOf<CachedVehicle>()
        vehicleEntities.forEach {
            cachedVehicles.add(entityMapper.mapToCached(it))
        }
        cachedVehicles.forEach {
            snappDatabase.cachedVehicleDao().insertVehicle(it)
        }

        val testObserver = vehicleCacheImp.getVehicles().test()

        testObserver.assertValue(vehicleEntities)
    }
}