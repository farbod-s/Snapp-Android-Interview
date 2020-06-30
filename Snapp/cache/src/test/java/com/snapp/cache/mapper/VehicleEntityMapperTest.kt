package com.snapp.cache.mapper

import com.snapp.cache.factory.VehicleFactory
import com.snapp.cache.model.CachedVehicle
import com.snapp.data.model.VehicleEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class VehicleEntityMapperTest {

    private lateinit var vehicleEntityMapper: VehicleEntityMapper

    @Before
    fun setUp() {
        vehicleEntityMapper = VehicleEntityMapper()
    }

    @Test
    fun mapToCached() {
        val vehicleEntity = VehicleFactory.generateVehicleEntity()
        val cachedVehicle = vehicleEntityMapper.mapToCached(vehicleEntity)

        assertDataEquality(vehicleEntity, cachedVehicle)
    }

    @Test
    fun mapFromCached() {
        val cachedVehicle = VehicleFactory.generateCachedVehicle()
        val vehicleEntity = vehicleEntityMapper.mapFromCached(cachedVehicle)

        assertDataEquality(vehicleEntity, cachedVehicle)
    }

    private fun assertDataEquality(
        vehicleEntity: VehicleEntity,
        cachedVehicle: CachedVehicle
    ) {
        assertEquals(vehicleEntity.id, cachedVehicle.id)
        assertEquals(vehicleEntity.type, cachedVehicle.type)
        assertEquals(vehicleEntity.bearing, cachedVehicle.bearing)
        assertEquals(vehicleEntity.imageUrl, cachedVehicle.imageUrl)
    }
}