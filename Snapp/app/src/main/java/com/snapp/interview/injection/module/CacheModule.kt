package com.snapp.interview.injection.module

import android.app.Application
import com.snapp.cache.VehicleCacheImp
import com.snapp.cache.db.SnappDatabase
import com.snapp.data.repository.VehicleCache
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Module that provides all dependencies from the cache package/layer.
 */
@Module
abstract class CacheModule {

    /**
     * This companion object annotated as a module is necessary in order to provide dependencies
     * statically in case the wrapping module is an abstract class (to use binding)
     */
    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideDatabase(application: Application): SnappDatabase {
            return SnappDatabase.getInstance(application.applicationContext)
        }
    }

    @Binds
    abstract fun bindCache(cacheImp: VehicleCacheImp): VehicleCache
}