package com.crazyworld.countries.data.remote

import io.reactivex.Single
import retrofit2.http.GET

interface RemoteDataSource {
    @GET("rest/v2/all")
    fun getAllCountries() : Single<List<CountriesResponse>>
}