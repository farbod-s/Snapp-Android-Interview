package com.snapp.presentation.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer layers
 *
 * @param <View>  the view model input type
 * @param <Model> the domain model output type
 */
interface Mapper<out View, in Model> {

    fun mapToView(type: Model): View
}