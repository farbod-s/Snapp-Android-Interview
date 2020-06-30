package com.snapp.cache.factory

import com.snapp.cache.model.CachedVehicle
import com.snapp.data.model.VehicleEntity

/**
 * Factory class for Vehicle related instances
 */
object VehicleFactory {

    fun generateVehicleEntity(): VehicleEntity {
        return VehicleEntity(
            DataFactory.randomInt(),
            DataFactory.randomType(),
            DataFactory.randomLatitude(),
            DataFactory.randomLongitude(),
            DataFactory.randomInt(),
            DataFactory.randomImage()
        )
    }

    fun generateVehicleEntityList(count: Int): List<VehicleEntity> {
        val entities = mutableListOf<VehicleEntity>()
        repeat(count) {
            entities.add(generateVehicleEntity())
        }
        return entities
    }

    fun generateCachedVehicles(count: Int): List<CachedVehicle> {
        val vehicles = mutableListOf<CachedVehicle>()
        repeat(count) {
            vehicles.add(createCachedVehicle())
        }
        return vehicles
    }

    fun generateCachedVehicle(): CachedVehicle {
        return createCachedVehicle()
    }

    private fun createCachedVehicle(): CachedVehicle {
        return CachedVehicle(
            DataFactory.randomInt(),
            DataFactory.randomType(),
            DataFactory.randomLatitude(),
            DataFactory.randomLongitude(),
            DataFactory.randomInt(),
            DataFactory.randomImage()
        )
    }
}