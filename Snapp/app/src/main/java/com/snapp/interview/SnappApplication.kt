package com.snapp.interview

import android.app.Activity
import android.app.Application
import com.snapp.interview.injection.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SnappApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        initializeInjector()
    }

    private fun initializeInjector() {
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun androidInjector() = activityDispatchingAndroidInjector as AndroidInjector<Any>
}