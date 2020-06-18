package com.snapp.interview.injection.module

import com.snapp.domain.executor.PostExecutionThread
import com.snapp.interview.executor.UiThread
import com.snapp.interview.ui.MainActivity
import com.snapp.interview.ui.browse.BrowseFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module that provides all dependencies from the mobile-ui package/layer and injects all activities.
 */
@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun provideListFragment(): BrowseFragment
}