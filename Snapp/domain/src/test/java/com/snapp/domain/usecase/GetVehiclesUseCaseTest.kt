package com.snapp.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.snapp.domain.executor.PostExecutionThread
import com.snapp.domain.executor.ThreadExecutor
import com.snapp.domain.model.Vehicle
import com.snapp.domain.repository.VehicleRepository
import com.snapp.domain.usecase.factory.VehicleFactory
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class GetVehiclesUseCaseTest {

    private lateinit var getVehiclesUseCase: GetVehiclesUseCase

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockRepository: VehicleRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockRepository = mock()

        getVehiclesUseCase = GetVehiclesUseCase(
            mockRepository,
            mockThreadExecutor,
            mockPostExecutionThread
        )
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        // verify mock repository behavior
        getVehiclesUseCase.buildUseCaseObservable()

        verify(mockRepository).getVehicles()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        // assert observable completion
        stubVehicleRepositoryGetVehicles(Flowable.just(VehicleFactory.generateVehicles(3)))

        val testObserver = getVehiclesUseCase.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        // assert observable received exact data
        val vehicles = VehicleFactory.generateVehicles(3)
        stubVehicleRepositoryGetVehicles(Flowable.just(vehicles))

        val testObserver = getVehiclesUseCase.buildUseCaseObservable().test()
        testObserver.assertValue(vehicles)
    }

    private fun stubVehicleRepositoryGetVehicles(data: Flowable<List<Vehicle>>) {
        // return data whenever we query mock repository
        whenever(mockRepository.getVehicles()).thenReturn(data)
    }
}