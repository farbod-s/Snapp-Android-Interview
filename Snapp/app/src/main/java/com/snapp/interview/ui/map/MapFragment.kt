package com.snapp.interview.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.snapp.interview.R
import com.snapp.interview.databinding.FragmentMapBinding
import com.snapp.interview.ui.common.BaseFragment
import com.snapp.interview.ui.utils.AndroidUtility
import com.snapp.presentation.VehicleViewModel
import com.snapp.presentation.data.ResourceState
import com.snapp.presentation.model.VehicleView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : BaseFragment(), OnMapReadyCallback, MarkerLoadCallback {

    private lateinit var map: GoogleMap
    private lateinit var viewModel: VehicleViewModel
    private lateinit var bounds: LatLngBounds.Builder

    private var vehiclesCount = 0
    private var loadedVehicles = 0

    // hold strong reference to load marker image
    private val markers = ArrayList<VehicleMarker>()

    companion object {
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMapBinding.inflate(inflater, container, false).root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel(VehicleViewModel::class.java)
        initializeMap()
    }

    private fun initializeMap() {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.view_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = true
        observeData()
    }

    private fun observeData() {
        viewModel.getVehicles().observe(this, Observer {
            it?.let {
                this.handleDataState(it.status, it.data, it.message)
            }
        })
    }

    private fun handleDataState(
        resourceState: ResourceState, data: List<VehicleView>?,
        message: String?
    ) {
        when (resourceState) {
            ResourceState.LOADING -> setupScreenForLoadingState()
            ResourceState.SUCCESS -> setupScreenForSuccess(data)
            ResourceState.ERROR -> setupScreenForError(message)
        }
    }

    private fun setupScreenForLoadingState() {
        view_loading.visibility = View.VISIBLE
        view_empty.visibility = View.GONE
        view_error.visibility = View.GONE
    }

    private fun setupScreenForSuccess(data: List<VehicleView>?) {
        view_loading.visibility = View.GONE
        view_error.visibility = View.GONE
        if (data != null && data.isNotEmpty()) {
            updateMarkers(data)
        } else {
            view_empty.visibility = View.VISIBLE
        }
    }

    private fun setupScreenForError(message: String?) {
        view_loading.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.VISIBLE
    }

    private fun updateMarkers(vehicles: List<VehicleView>) {
        bounds = LatLngBounds.builder()
        vehiclesCount = vehicles.size

        for (vehicle in vehicles) {
            val target = VehicleMarker(vehicle, this)
            markers.add(target)
            Picasso.get()
                .load(vehicle.imageUrl)
                .into(target)
        }
    }

    override fun onMarkerLoaded(marker: MarkerOptions) {
        map.addMarker(marker)
        bounds.include(marker.position)
        loadedVehicles++

        // check all vehicles are loaded to animate camera into the bounds
        if (loadedVehicles == vehiclesCount) {
            map.animateCamera(
                CameraUpdateFactory.newLatLngBounds(
                    bounds.build(),
                    AndroidUtility.convertDpToPixel(activity, 85.toFloat())
                )
            )
            loadedVehicles = 0
            markers.clear()
        }
    }
}