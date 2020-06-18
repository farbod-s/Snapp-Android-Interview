package com.snapp.interview.injection.module

import com.snapp.data.VehicleDataRepository
import com.snapp.data.executor.JobExecutor
import com.snapp.domain.executor.ThreadExecutor
import com.snapp.domain.repository.VehicleRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindRepository(dataRepository: VehicleDataRepository): VehicleRepository

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor
}