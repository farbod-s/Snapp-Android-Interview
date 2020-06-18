package com.snapp.cache

import com.snapp.cache.db.SnappDatabase
import com.snapp.cache.mapper.VehicleEntityMapper
import com.snapp.data.model.VehicleEntity
import com.snapp.data.repository.VehicleCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Vehicle instances. This class implements the
 * [VehicleCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class VehicleCacheImp @Inject constructor(
    private val snappDatabase: SnappDatabase,
    private val entityMapper: VehicleEntityMapper,
    private val preferencesHelper: PreferencesHelper
) :
    VehicleCache {

    private val EXPIRATION_TIME_MILLISECONDS by lazy {
        (60 * 10 * 1000).toLong()
    }

    /**
     * Retrieve an instance from the database, used for tests.
     */
    internal fun getDatabase(): SnappDatabase {
        return snappDatabase
    }

    /**
     * Remove all the data from all the tables in the database.
     */
    override fun clearVehicles(): Completable {
        return Completable.defer {
            snappDatabase.cachedVehicleDao().clearVehicles()
            Completable.complete()
        }
    }

    /**
     * Save the given list of [VehicleEntity] instances to the database.
     */
    override fun saveVehicles(vehicles: List<VehicleEntity>): Completable {
        return Completable.defer {
            vehicles.forEach {
                snappDatabase.cachedVehicleDao().insertVehicle(
                    entityMapper.mapToCached(it)
                )
            }
            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [VehicleEntity] instances from the database.
     */
    override fun getVehicles(): Flowable<List<VehicleEntity>> {
        return Flowable.defer {
            Flowable.just(snappDatabase.cachedVehicleDao().getVehicles())
        }.map {
            it.map { entityMapper.mapFromCached(it) }
        }
    }

    /**
     * Check whether there are instances of [CachedVehicle] stored in the cache.
     */
    override fun isCached(): Single<Boolean> {
        return Single.defer {
            Single.just(snappDatabase.cachedVehicleDao().getVehicles().isNotEmpty())
        }
    }

    /**
     * Set a point in time at when the cache was last updated.
     */
    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME_MILLISECONDS] time.
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME_MILLISECONDS
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }
}