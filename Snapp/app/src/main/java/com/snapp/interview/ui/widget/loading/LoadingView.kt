package com.snapp.interview.ui.widget.loading

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.snapp.interview.databinding.ViewLoadingBinding

/**
 * Widget used to display a loading state to the user
 */
class LoadingView : RelativeLayout {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        ViewLoadingBinding.inflate(LayoutInflater.from(context), this)
    }
}