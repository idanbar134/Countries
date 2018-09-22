package com.crazyworld.countries.view.countries

import com.crazyworld.countries.data.remote.CountriesResponse
import com.crazyworld.countries.data.remote.source.RemoteDataSource
import io.reactivex.Single

interface CountriesRepository {
    fun getAllCountries() : Single<List<CountriesResponse>>
}

class CountriesRepositoryImpl(private val remote: RemoteDataSource): CountriesRepository {
    override fun getAllCountries(): Single<List<CountriesResponse>> = remote.getAllCountries()
}