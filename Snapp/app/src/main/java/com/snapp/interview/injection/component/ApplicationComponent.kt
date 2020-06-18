package com.snapp.interview.injection.component

import android.app.Application
import com.snapp.interview.SnappApplication
import com.snapp.interview.injection.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        CacheModule::class, DataModule::class,
        DomainModule::class,
        PresentationModule::class,
        RemoteModule::class,
        UiModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: SnappApplication)
}