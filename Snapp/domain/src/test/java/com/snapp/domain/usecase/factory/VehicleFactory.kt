package com.snapp.domain.usecase.factory

import com.snapp.domain.model.Vehicle
import com.snapp.domain.usecase.factory.DataFactory.Factory.randomImage
import com.snapp.domain.usecase.factory.DataFactory.Factory.randomInt
import com.snapp.domain.usecase.factory.DataFactory.Factory.randomLatitude
import com.snapp.domain.usecase.factory.DataFactory.Factory.randomLongitude
import com.snapp.domain.usecase.factory.DataFactory.Factory.randomType

/**
 * Factory class for Vehicle related instances
 */
class VehicleFactory {

    companion object Factory {

        fun generateVehicles(count: Int): List<Vehicle> {
            val vehicles = mutableListOf<Vehicle>()
            repeat(count) {
                vehicles.add(createVehicle())
            }
            return vehicles
        }

        private fun createVehicle(): Vehicle {
            return Vehicle(
                randomInt(),
                randomType(),
                randomLatitude(),
                randomLongitude(),
                randomInt(),
                randomImage()
            )
        }
    }
}