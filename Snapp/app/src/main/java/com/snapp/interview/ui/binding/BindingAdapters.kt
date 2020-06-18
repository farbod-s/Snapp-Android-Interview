package com.snapp.interview.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapters {

    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
    fun loadImageFromUrl(
        view: ImageView?,
        imageUrl: String?,
        placeholder: Int
    ) {
        when {
            placeholder > 0 -> Picasso.get()
                .load(imageUrl)
                .placeholder(placeholder)
                .into(view)
            else -> Picasso.get()
                .load(imageUrl)
                .into(view)
        }
    }
}