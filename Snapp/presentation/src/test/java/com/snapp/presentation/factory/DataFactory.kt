package com.snapp.presentation.factory

import java.util.concurrent.ThreadLocalRandom

/**
 * Factory class for data instances
 */
object DataFactory {

    private val images = arrayListOf(
        "https://snapp.ir/assets/test/snapp_map@2x.png",
        "https://snapp.ir/assets/test/snapp_map_st2.png"
    )

    private val types = arrayListOf("ECO", "PLUS")

    fun randomType(): String {
        return types.random()
    }

    fun randomImage(): String {
        return images.random()
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(1, 360)
    }

    fun randomLongitude(): Double {
        return ThreadLocalRandom.current().nextDouble(51.4091973, 51.4094662)
    }

    fun randomLatitude(): Double {
        return ThreadLocalRandom.current().nextDouble(35.7570448, 35.7580966)
    }
}