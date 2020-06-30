package com.snapp.data.source

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.snapp.data.repository.VehicleCache
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class VehicleDataStoreFactoryTest {

    private lateinit var vehicleCache: VehicleCache
    private lateinit var vehicleCacheDataStore: VehicleCacheDataStore
    private lateinit var vehicleRemoteDataStore: VehicleRemoteDataStore

    private lateinit var vehicleDataStoreFactory: VehicleDataStoreFactory

    @Before
    fun setUp() {
        vehicleCache = mock()
        vehicleCacheDataStore = mock()
        vehicleRemoteDataStore = mock()

        vehicleDataStoreFactory = VehicleDataStoreFactory(
            vehicleCache,
            vehicleCacheDataStore,
            vehicleRemoteDataStore
        )
    }

    @Test
    fun retrieveRemoteDataStoreReturnsRemoteDataStore() {
        // Retrieve Remote Data Store
        val dataStore = vehicleDataStoreFactory.retrieveRemoteDataStore()

        assert(dataStore is VehicleRemoteDataStore)
    }

    @Test
    fun retrieveCacheDataStoreReturnsCacheDataStore() {
        // Retrieve Cache Data Store
        val dataStore = vehicleDataStoreFactory.retrieveCacheDataStore()

        assert(dataStore is VehicleCacheDataStore)
    }

    @Test
    fun retrieveDataStoreReturnsCacheDataStore() {
        stubDataIsCached(Single.just(true))
        stubDataIsExpired(false)

        val dataStore = vehicleDataStoreFactory.retrieveDataStore(true)

        assert(dataStore is VehicleCacheDataStore)
    }

    @Test
    fun retrieveDataStoreWhenNotCachedReturnsRemoteDataStore() {
        stubDataIsCached(Single.just(false))

        val dataStore = vehicleDataStoreFactory.retrieveDataStore(false)

        assert(dataStore is VehicleRemoteDataStore)
    }

    @Test
    fun retrieveDataStoreWhenCacheExpiredReturnsRemoteDataStore() {
        stubDataIsCached(Single.just(true))
        stubDataIsExpired(true)

        val dataStore = vehicleDataStoreFactory.retrieveDataStore(true)

        assert(dataStore is VehicleRemoteDataStore)
    }

    private fun stubDataIsCached(isCached: Single<Boolean>) {
        whenever(vehicleCache.isCached()).thenReturn(isCached)
    }

    private fun stubDataIsExpired(isExpired: Boolean) {
        whenever(vehicleCache.isExpired()).thenReturn(isExpired)
    }
}