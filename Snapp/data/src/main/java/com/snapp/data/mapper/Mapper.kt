package com.snapp.data.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <Entity> the cached model input type
 * @param <Model> the remote model input type
 */
interface Mapper<Entity, Model> {

    fun mapFromEntity(type: Entity): Model

    fun mapToEntity(type: Model): Entity
}