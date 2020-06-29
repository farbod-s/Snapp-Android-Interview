package com.snapp.data.factory

import com.snapp.data.model.VehicleEntity
import com.snapp.domain.model.Vehicle

/**
 * Factory class for Vehicle related instances
 */
class VehicleFactory {

    companion object Factory {

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

        fun generateVehicles(count: Int): List<Vehicle> {
            val vehicles = mutableListOf<Vehicle>()
            repeat(count) {
                vehicles.add(createVehicle())
            }
            return vehicles
        }

        fun generateVehicle(): Vehicle {
            return createVehicle()
        }

        private fun createVehicle(): Vehicle {
            return Vehicle(
                DataFactory.randomInt(),
                DataFactory.randomType(),
                DataFactory.randomLatitude(),
                DataFactory.randomLongitude(),
                DataFactory.randomInt(),
                DataFactory.randomImage()
            )
        }
    }
}