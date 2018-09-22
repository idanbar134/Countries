package com.crazyworld.countries.data.remote.source

import com.crazyworld.countries.data.remote.CountriesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RemoteDataSource {
    @GET("rest/v2/all")
    fun getAllCountries() : Single<List<CountriesResponse>>
}