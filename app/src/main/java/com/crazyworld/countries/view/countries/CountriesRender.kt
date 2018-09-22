package com.crazyworld.countries.view.countries

import android.os.Parcel
import android.os.Parcelable
import com.crazyworld.countries.common.ViewType

data class CountriesRender(var name: String? = null,
                           var flag: String? = null,
                           var population: String? = null) : ViewType, Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun getViewType(): Int = CountryItemType.ItemType
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(flag)
        parcel.writeString(population)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountriesRender> {
        override fun createFromParcel(parcel: Parcel): CountriesRender {
            return CountriesRender(parcel)
        }

        override fun newArray(size: Int): Array<CountriesRender?> {
            return arrayOfNulls(size)
        }
    }
}