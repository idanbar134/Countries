package com.crazyworld.countries.view.detail

import com.crazyworld.countries.R
import com.crazyworld.countries.common.BaseFragment
import com.crazyworld.countries.common.Constants
import com.crazyworld.countries.view.countries.CountriesRender
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.MapStyleOptions


class DetailsFragment : BaseFragment(), OnMapReadyCallback {
    private val country by lazy {
        arguments?.getParcelable<CountriesRender>(Constants.BUNDLE_COUNTRY)
    }

    override fun resource(): Int = R.layout.detail_fragment

    override fun render() {
        initMap()
    }

    private fun initMap() {
        val mapFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

    }

    override fun onMapReady(map: GoogleMap?) {
        map?.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        activity, R.raw.map_style))
        map?.animateCamera(CameraUpdateFactory.zoomTo(2f))
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(country?.latLng,12f))
    }
}