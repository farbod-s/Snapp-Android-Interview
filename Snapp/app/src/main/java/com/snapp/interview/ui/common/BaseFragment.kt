package com.snapp.interview.ui.common

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.snapp.interview.ui.MainActivity
import com.snapp.presentation.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var activity: MainActivity

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        activity = (context as MainActivity)
    }

    protected open fun <VM : ViewModel?> getViewModel(modelClass: Class<VM>): VM {
        return ViewModelProviders.of(this, viewModelFactory).get(modelClass)
    }
}