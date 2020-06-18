package com.snapp.domain.usecase

import com.snapp.domain.executor.PostExecutionThread
import com.snapp.domain.executor.ThreadExecutor
import com.snapp.domain.model.Vehicle
import com.snapp.domain.repository.VehicleRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Use case used for retreiving a [List] of [Vehicle] instances from the [VehicleRepository]
 */
open class GetVehiclesUseCase @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) :
    UseCase<List<Vehicle>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flowable<List<Vehicle>> {
        return vehicleRepository.getVehicles()
    }
}