package com.snapp.remote.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <Model> the remote model input type
 * @param <Entity> the entity model output type
 */
interface EntityMapper<in Model, out Entity> {

    fun mapFromRemote(type: Model): Entity
}