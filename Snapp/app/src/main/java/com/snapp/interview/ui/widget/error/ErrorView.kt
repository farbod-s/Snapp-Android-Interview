package com.snapp.interview.ui.widget.error

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.snapp.interview.databinding.ViewErrorBinding
import kotlinx.android.synthetic.main.view_error.view.*

/**
 * Widget used to display an error state to the user
 */
class ErrorView : RelativeLayout {

    var errorListener: ErrorListener? = null

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
        ViewErrorBinding.inflate(LayoutInflater.from(context), this)
        button_try_again.setOnClickListener { errorListener?.onTryAgainClicked() }
    }
}