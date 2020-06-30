package com.snapp.data.source

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.snapp.data.factory.VehicleFactory
import com.snapp.data.model.VehicleEntity
import com.snapp.data.repository.VehicleRemote
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class VehicleRemoteDataStoreTest {

    private lateinit var vehicleRemote: VehicleRemote
    private lateinit var vehicleRemoteDataStore: VehicleRemoteDataStore

    @Before
    fun setUp() {
        vehicleRemote = mock()
        vehicleRemoteDataStore = VehicleRemoteDataStore(vehicleRemote)
    }

    @Test
    fun getVehiclesCompletes() {
        stubGetVehicles(Flowable.just(VehicleFactory.generateVehicleEntityList(3)))
        val testObserver = vehicleRemote.getVehicles().test()
        testObserver.assertComplete()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveVehiclesThrowsException() {
        vehicleRemoteDataStore.saveVehicles(VehicleFactory.generateVehicleEntityList(3)).test()
    }

    private fun stubGetVehicles(single: Flowable<List<VehicleEntity>>) {
        whenever(vehicleRemote.getVehicles()).thenReturn(single)
    }
}