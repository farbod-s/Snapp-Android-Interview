package com.snapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.snapp.domain.model.Vehicle
import com.snapp.domain.usecase.GetVehiclesUseCase
import com.snapp.presentation.data.ResourceState
import com.snapp.presentation.factory.VehicleFactory
import com.snapp.presentation.mapper.VehicleMapper
import com.snapp.presentation.model.VehicleView
import io.reactivex.subscribers.DisposableSubscriber
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor

@RunWith(JUnit4::class)
class VehicleViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var vehiclesUseCase: GetVehiclesUseCase
    lateinit var vehicleMapper: VehicleMapper
    lateinit var vehicleViewModel: VehicleViewModel

    @Captor
    private lateinit var captor: KArgumentCaptor<DisposableSubscriber<List<Vehicle>>>

    @Before
    fun setUp() {
        vehiclesUseCase = mock()
        vehicleMapper = mock()
        vehicleViewModel = VehicleViewModel(vehiclesUseCase, vehicleMapper)

        captor = argumentCaptor()
    }

    @Test
    fun getVehiclesReturnsSuccess() {
        val list = VehicleFactory.generateVehicles(1)
        val viewList = VehicleFactory.generateVehicleViewList(1)
        stubMapperMapToView(viewList[0], list[0])

        vehicleViewModel.getVehicles()

        verify(vehiclesUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(list)

        assert(vehicleViewModel.getVehicles().value?.status == ResourceState.SUCCESS)
    }

    @Test
    fun getVehiclesReturnsDataOnSuccess() {
        val list = VehicleFactory.generateVehicles(1)
        val viewList = VehicleFactory.generateVehicleViewList(1)
        stubMapperMapToView(viewList[0], list[0])

        vehicleViewModel.getVehicles()

        verify(vehiclesUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(list)

        assert(vehicleViewModel.getVehicles().value?.data == viewList)
    }

    @Test
    fun getVehiclesReturnsError() {
        vehicleViewModel.getVehicles()

        verify(vehiclesUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assert(vehicleViewModel.getVehicles().value?.status == ResourceState.ERROR)
    }

    @Test
    fun getVehiclesFailsAndContainsNoData() {
        vehicleViewModel.getVehicles()

        verify(vehiclesUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assert(vehicleViewModel.getVehicles().value?.data == null)
    }

    @Test
    fun getVehiclesReturnsLoading() {
        vehicleViewModel.getVehicles()

        assert(vehicleViewModel.getVehicles().value?.status == ResourceState.LOADING)
    }

    @Test
    fun getVehiclesContainsNoDataWhenLoading() {
        vehicleViewModel.getVehicles()

        assert(vehicleViewModel.getVehicles().value?.data == null)
    }

    private fun stubMapperMapToView(
        vehicleView: VehicleView,
        vehicle: Vehicle
    ) {
        whenever(vehicleMapper.mapToView(vehicle)).thenReturn(vehicleView)
    }
}