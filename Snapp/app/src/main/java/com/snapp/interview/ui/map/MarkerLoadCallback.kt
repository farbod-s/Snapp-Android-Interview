package com.snapp.interview.ui.map

import com.google.android.gms.maps.model.MarkerOptions

interface MarkerLoadCallback {

    fun onMarkerLoaded(marker: MarkerOptions)
}