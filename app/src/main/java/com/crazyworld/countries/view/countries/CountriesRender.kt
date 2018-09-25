package com.crazyworld.countries.view.countries

import android.os.Parcelable
import com.crazyworld.countries.common.ViewType
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountriesRender(var name: String? = null,
                           var flag: String? = null,
                           var population: String? = null,
                           var latLng: LatLng? = null) : ViewType, Parcelable {

    override fun getViewType(): Int = CountryItemType.ItemType

}