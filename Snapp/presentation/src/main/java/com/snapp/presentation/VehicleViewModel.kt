package com.snapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snapp.domain.model.Vehicle
import com.snapp.domain.usecase.GetVehiclesUseCase
import com.snapp.presentation.data.Resource
import com.snapp.presentation.data.ResourceState
import com.snapp.presentation.mapper.VehicleMapper
import com.snapp.presentation.model.VehicleView
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class VehicleViewModel @Inject internal constructor(
    private val vehiclesUseCase: GetVehiclesUseCase,
    private val vehicleMapper: VehicleMapper
) : ViewModel() {

    private val vehiclesLiveData: MutableLiveData<Resource<List<VehicleView>>> =
        MutableLiveData()

    init {
        fetchVehicles()
    }

    override fun onCleared() {
        vehiclesUseCase.dispose()
        super.onCleared()
    }

    fun getVehicles(): LiveData<Resource<List<VehicleView>>> {
        return vehiclesLiveData
    }

    fun fetchVehicles() {
        vehiclesLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return vehiclesUseCase.execute(VehiclesSubscriber())
    }

    inner class VehiclesSubscriber : DisposableSubscriber<List<Vehicle>>() {

        override fun onComplete() {}

        override fun onNext(t: List<Vehicle>) {
            vehiclesLiveData.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    t.map { vehicleMapper.mapToView(it) }, null
                )
            )
        }

        override fun onError(exception: Throwable) {
            vehiclesLiveData.postValue(Resource(ResourceState.ERROR, null, exception.message))
        }
    }
}