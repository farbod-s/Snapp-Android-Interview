package com.snapp.interview.ui.common

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * A generic RecyclerView adapter that uses Data Binding.
 *
 * @param <Item> Type of the items in the list
 * @param <Binding> The type of the ViewDataBinding
 */
abstract class BaseAdapter<Item, Binding : ViewDataBinding> :
    RecyclerView.Adapter<BaseViewHolder<Binding>>() {

    private var items: List<Item> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Binding> {
        val binding = createBinding(parent)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Binding>, position: Int) {
        bind(holder.binding, items[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    protected abstract fun createBinding(parent: ViewGroup): Binding
    protected abstract fun bind(binding: Binding, item: Item)
}