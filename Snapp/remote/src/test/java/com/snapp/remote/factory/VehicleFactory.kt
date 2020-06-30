package com.snapp.remote.factory

import com.snapp.remote.model.VehicleModel
import com.snapp.remote.model.VehiclesResponse

/**
 * Factory class for Vehicle related instances
 */
class VehicleFactory {

    companion object Factory {

        fun generateVehicleResponse(): VehiclesResponse {
            return VehiclesResponse(generateVehicleModelList(3))
        }

        fun generateVehicleModelList(count: Int): List<VehicleModel> {
            val models = mutableListOf<VehicleModel>()
            repeat(count) {
                models.add(generateVehicleModel())
            }
            return models
        }

        fun generateVehicleModel(): VehicleModel {
            return VehicleModel(
                DataFactory.randomType(),
                DataFactory.randomLatitude(),
                DataFactory.randomLongitude(),
                DataFactory.randomInt(),
                DataFactory.randomImage()
            )
        }
    }
}