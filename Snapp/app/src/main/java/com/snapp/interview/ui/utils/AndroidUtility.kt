package com.snapp.interview.ui.utils

import android.content.Context

object AndroidUtility {

    fun convertDpToPixel(context: Context, dp: Float): Int {
        val px: Float = dp * (context.resources.displayMetrics.densityDpi / 160f)
        return px.toInt()
    }

    fun convertPixelsToDp(context: Context, px: Float): Int {
        val dp: Float = px / (context.resources.displayMetrics.densityDpi / 160f)
        return dp.toInt()
    }
}