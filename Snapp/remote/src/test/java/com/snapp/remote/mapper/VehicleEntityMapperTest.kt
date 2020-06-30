package com.snapp.remote.mapper

import com.snapp.remote.factory.VehicleFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class VehicleEntityMapperTest {

    private lateinit var entityMapper: VehicleEntityMapper

    @Before
    fun setUp() {
        entityMapper = VehicleEntityMapper()
    }

    @Test
    fun mapFromRemote() {
        val vehicleModel = VehicleFactory.generateVehicleModel()
        val vehicleEntity = entityMapper.mapFromRemote(vehicleModel)

        assertEquals(vehicleModel.type, vehicleEntity.type)
        assertEquals(vehicleModel.bearing, vehicleEntity.bearing)
        assertEquals(vehicleModel.imageUrl, vehicleEntity.imageUrl)
    }
}