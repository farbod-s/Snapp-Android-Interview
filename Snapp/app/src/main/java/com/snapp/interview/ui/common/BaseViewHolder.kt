package com.snapp.interview.ui.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * A generic ViewHolder that works with a [ViewDataBinding].
 * @param <Binding> The type of the ViewDataBinding.
 */
class BaseViewHolder<out Binding : ViewDataBinding> constructor(val binding: Binding) :
    RecyclerView.ViewHolder(binding.root)