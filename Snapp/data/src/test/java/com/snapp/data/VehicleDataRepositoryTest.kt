package com.snapp.data

import com.nhaarman.mockitokotlin2.*
import com.snapp.data.factory.VehicleFactory
import com.snapp.data.mapper.VehicleMapper
import com.snapp.data.model.VehicleEntity
import com.snapp.data.repository.VehicleDataStore
import com.snapp.data.source.VehicleCacheDataStore
import com.snapp.data.source.VehicleDataStoreFactory
import com.snapp.data.source.VehicleRemoteDataStore
import com.snapp.domain.model.Vehicle
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class VehicleDataRepositoryTest {

    private lateinit var vehicleMapper: VehicleMapper
    private lateinit var vehicleDataStoreFactory: VehicleDataStoreFactory
    private lateinit var vehicleDataRepository: VehicleDataRepository

    private lateinit var vehicleCacheDataStore: VehicleCacheDataStore
    private lateinit var vehicleRemoteDataStore: VehicleRemoteDataStore

    @Before
    fun setUp() {
        vehicleMapper = mock()
        vehicleDataStoreFactory = mock()
        vehicleDataRepository = VehicleDataRepository(
            vehicleDataStoreFactory,
            vehicleMapper
        )

        vehicleCacheDataStore = mock()
        vehicleRemoteDataStore = mock()

        stubDataStoreFactoryRetrieveCacheDataStore()
        stubDataStoreFactoryRetrieveRemoteDataStore()
    }

    @Test
    fun saveVehiclesCallsCacheDataStore() {
        stubSaveVehicles(Completable.complete())

        vehicleDataRepository.saveVehicles(VehicleFactory.generateVehicles(3)).test()

        verify(vehicleCacheDataStore).saveVehicles(any())
    }

    @Test
    fun saveVehiclesNeverCallsRemoteDataStore() {
        stubSaveVehicles(Completable.complete())

        vehicleDataRepository.saveVehicles(VehicleFactory.generateVehicles(3)).test()

        verify(vehicleRemoteDataStore, never()).saveVehicles(any())
    }

    @Test
    fun getVehiclesReturnsData() {
        // Given
        stubCacheDataStoreIsCached(Single.just(true))
        stubDataStoreFactoryRetrieveDataStore(vehicleCacheDataStore)
        stubSaveVehicles(Completable.complete())
        val vehicles = VehicleFactory.generateVehicles(3)
        val vehicleEntities = VehicleFactory.generateVehicleEntityList(3)
        vehicles.forEachIndexed { index, vehicle ->
            stubMapper(vehicleEntities[index], vehicle)
        }
        stubCacheDataStoreGetVehicles(Flowable.just(vehicleEntities))

        // When
        val testObserver = vehicleDataRepository.getVehicles().test()

        // Then
        testObserver.assertValue(vehicles)
    }

    private fun stubSaveVehicles(completable: Completable) {
        whenever(vehicleCacheDataStore.saveVehicles(any())).thenReturn(completable)
    }

    private fun stubCacheDataStoreIsCached(isCached: Single<Boolean>) {
        whenever(vehicleCacheDataStore.isCached()).thenReturn(isCached)
    }

    private fun stubCacheDataStoreGetVehicles(data: Flowable<List<VehicleEntity>>) {
        whenever(vehicleCacheDataStore.getVehicles()).thenReturn(data)
    }

    private fun stubDataStoreFactoryRetrieveCacheDataStore() {
        whenever(vehicleDataStoreFactory.retrieveCacheDataStore()).thenReturn(vehicleCacheDataStore)
    }

    private fun stubDataStoreFactoryRetrieveRemoteDataStore() {
        whenever(vehicleDataStoreFactory.retrieveRemoteDataStore()).thenReturn(
            vehicleRemoteDataStore
        )
    }

    private fun stubDataStoreFactoryRetrieveDataStore(dataStore: VehicleDataStore) {
        whenever(vehicleDataStoreFactory.retrieveDataStore(any())).thenReturn(dataStore)
    }

    private fun stubMapper(
        vehicleEntity: VehicleEntity,
        vehicle: Vehicle
    ) {
        whenever(vehicleMapper.mapFromEntity(vehicleEntity)).thenReturn(vehicle)
    }
}