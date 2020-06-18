package com.snapp.cache.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <Model> the cached model input type
 * @param <Entity> the entity model input type
 */
interface EntityMapper<Model, Entity> {

    fun mapFromCached(type: Model): Entity

    fun mapToCached(type: Entity): Model
}