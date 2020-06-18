package com.snapp.interview.ui.map

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.snapp.presentation.model.VehicleView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class VehicleMarker(
    private val vehicle: VehicleView,
    private val listener: MarkerLoadCallback?
) : Target {

    private var marker: MarkerOptions = MarkerOptions()
        .position(LatLng(vehicle.lat, vehicle.lng))
        .title(vehicle.type)
        .anchor(.5f, .5f)
        .rotation(vehicle.bearing.toFloat())

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        // nothing!
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        // nothing!
    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        marker.icon(BitmapDescriptorFactory.fromBitmap(bitmap))
        listener?.onMarkerLoaded(marker)
    }
}