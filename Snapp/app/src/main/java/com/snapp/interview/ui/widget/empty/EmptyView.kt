package com.snapp.interview.ui.widget.empty

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.snapp.interview.databinding.ViewEmptyBinding
import kotlinx.android.synthetic.main.view_empty.view.*

/**
 * Widget used to display an empty state to the user
 */
class EmptyView : RelativeLayout {

    var emptyListener: EmptyListener? = null

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
        ViewEmptyBinding.inflate(LayoutInflater.from(context), this)
        button_check_again.setOnClickListener { emptyListener?.onCheckAgainClicked() }
    }
}