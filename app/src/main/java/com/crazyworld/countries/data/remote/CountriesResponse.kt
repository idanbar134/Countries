package com.crazyworld.countries.data.remote

import com.google.gson.annotations.SerializedName

data class CountriesResponse(val name: String? = null,
                             val capital: String? = null,
                             val region: String? = null,
                             @SerializedName("latlng") val latLong: List<Double>? = null,
                             val languages: List<Languages>? = null,
                             val currencies: List<Currencies>? = null,
                             val flag: String? = null,
                             val population: String? = null)

data class Languages(val name: String)
data class Currencies(val name: String)
