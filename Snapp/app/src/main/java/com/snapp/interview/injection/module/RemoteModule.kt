package com.snapp.interview.injection.module

import com.snapp.data.repository.VehicleRemote
import com.snapp.interview.BuildConfig
import com.snapp.remote.VehicleRemoteImp
import com.snapp.remote.service.SnappService
import com.snapp.remote.service.SnappServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Module that provides all dependencies from the repository package/layer.
 */
@Module
abstract class RemoteModule {

    /**
     * This companion object annotated as a module is necessary in order to provide dependencies
     * statically in case the wrapping module is an abstract class (to use binding)
     */
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideApiService(): SnappService {
            return SnappServiceFactory.createSnappService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindRemote(remoteImpl: VehicleRemoteImp): VehicleRemote
}