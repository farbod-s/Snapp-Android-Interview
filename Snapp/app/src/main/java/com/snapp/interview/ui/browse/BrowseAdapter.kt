package com.snapp.interview.ui.browse

import android.view.LayoutInflater
import android.view.ViewGroup
import com.snapp.interview.databinding.ItemVehicleBinding
import com.snapp.interview.ui.common.BaseAdapter
import com.snapp.presentation.model.VehicleView
import javax.inject.Inject

class BrowseAdapter @Inject constructor() : BaseAdapter<VehicleView, ItemVehicleBinding>() {

    override fun createBinding(parent: ViewGroup): ItemVehicleBinding {
        return ItemVehicleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bind(binding: ItemVehicleBinding, item: VehicleView) {
        binding.vehicle = item
    }
}