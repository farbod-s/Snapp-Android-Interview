package com.snapp.data.mapper

import com.snapp.data.factory.VehicleFactory
import com.snapp.data.model.VehicleEntity
import com.snapp.domain.model.Vehicle
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class VehicleMapperTest {

    private lateinit var vehicleMapper: VehicleMapper

    @Before
    fun setUp() {
        vehicleMapper = VehicleMapper()
    }

    @Test
    fun mapFromEntity() {
        val vehicleEntity = VehicleFactory.generateVehicleEntity()
        val vehicle = vehicleMapper.mapFromEntity(vehicleEntity)

        assertDataEquality(vehicleEntity, vehicle)
    }

    @Test
    fun mapToEntity() {
        val vehicle = VehicleFactory.generateVehicle()
        val vehicleEntity = vehicleMapper.mapToEntity(vehicle)

        assertDataEquality(vehicleEntity, vehicle)
    }

    private fun assertDataEquality(
        vehicleEntity: VehicleEntity,
        vehicle: Vehicle
    ) {
        assertEquals(vehicleEntity.id, vehicle.id)
        assertEquals(vehicleEntity.type, vehicle.type)
        assertEquals(vehicleEntity.lat, vehicle.lat)
        assertEquals(vehicleEntity.lng, vehicle.lng)
        assertEquals(vehicleEntity.bearing, vehicle.bearing)
        assertEquals(vehicleEntity.imageUrl, vehicle.imageUrl)
    }
}