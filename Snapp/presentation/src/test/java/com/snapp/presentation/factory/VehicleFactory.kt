package com.snapp.presentation.factory

import com.snapp.domain.model.Vehicle
import com.snapp.presentation.model.VehicleView

/**
 * Factory class for Vehicle related instances
 */
object VehicleFactory {

    fun generateVehicles(count: Int): List<Vehicle> {
        val vehicles = mutableListOf<Vehicle>()
        repeat(count) {
            vehicles.add(generateVehicle())
        }
        return vehicles
    }

    fun generateVehicle(): Vehicle {
        return Vehicle(
            DataFactory.randomInt(),
            DataFactory.randomType(),
            DataFactory.randomLatitude(),
            DataFactory.randomLongitude(),
            DataFactory.randomInt(),
            DataFactory.randomImage()
        )
    }

    fun generateVehicleViewList(count: Int): List<VehicleView> {
        val models = mutableListOf<VehicleView>()
        repeat(count) {
            models.add(generateVehicleView())
        }
        return models
    }

    fun generateVehicleView(): VehicleView {
        return VehicleView(
            DataFactory.randomInt(),
            DataFactory.randomType(),
            DataFactory.randomLatitude(),
            DataFactory.randomLongitude(),
            DataFactory.randomInt(),
            DataFactory.randomImage()
        )
    }
}