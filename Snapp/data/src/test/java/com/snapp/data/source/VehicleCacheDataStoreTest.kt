package com.snapp.data.source

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.snapp.data.factory.VehicleFactory
import com.snapp.data.model.VehicleEntity
import com.snapp.data.repository.VehicleCache
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class VehicleCacheDataStoreTest {

    private lateinit var vehicleCache: VehicleCache
    private lateinit var vehicleCacheDataStore: VehicleCacheDataStore

    @Before
    fun setUp() {
        vehicleCache = mock()
        vehicleCacheDataStore = VehicleCacheDataStore(vehicleCache)
    }

    @Test
    fun getVehiclesCompletes() {
        // assert get vehicles completion
        stubGetVehicles(Flowable.just(VehicleFactory.generateVehicleEntityList(3)))

        val testObserver = vehicleCacheDataStore.getVehicles().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveVehiclesCompletes() {
        // assert save vehicles completion
        stubSaveVehicles(Completable.complete())

        val testObserver =
            vehicleCacheDataStore.saveVehicles(VehicleFactory.generateVehicleEntityList(3)).test()
        testObserver.assertComplete()
    }

    private fun stubGetVehicles(data: Flowable<List<VehicleEntity>>) {
        whenever(vehicleCache.getVehicles()).thenReturn(data)
    }

    private fun stubSaveVehicles(completable: Completable) {
        whenever(vehicleCache.saveVehicles(any())).thenReturn(completable)
    }
}