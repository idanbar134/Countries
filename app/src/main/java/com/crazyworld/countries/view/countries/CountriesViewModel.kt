package com.crazyworld.countries.view.countries

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.crazyworld.countries.common.AbstractViewModel
import com.crazyworld.countries.common.SchedulerProvider
import com.crazyworld.countries.common.with
import com.crazyworld.countries.data.remote.CountriesResponse
import com.google.android.gms.maps.model.LatLng

sealed class CountriesState
data class DataReady(val data: List<CountriesRender>) : CountriesState()

class CountriesViewModel(private val repository: CountriesRepository,
                         private val provider: SchedulerProvider) : AbstractViewModel() {

    val state = MutableLiveData<CountriesState>()

    init {
        getCountries()
    }

    private fun getCountries() = launch {
        repository.getAllCountries()
                .with(provider)
                .toObservable()
                .doOnSubscribe { progress.value = View.VISIBLE }
                .doFinally { progress.value = View.GONE }
                .flatMapIterable { it }
                .map { toCountriesRender(it) }
                .toList()
                .subscribe(::success, ::failure)
    }

    private fun success(countries: MutableList<CountriesRender>) {
        countries.shuffle()
        state.value = DataReady(countries.take(30))
    }

    private fun toCountriesRender(res: CountriesResponse) = CountriesRender().apply {
        name = res.name
        flag = res.flag
        population = res.population
        if (res.latLong.isNotEmpty()) {
            latLng = LatLng(res.latLong[0], res.latLong[1])
        }
    }
}