package com.crazyworld.countries.di

import com.crazyworld.countries.common.ApplicationSchedulerProvider
import com.crazyworld.countries.common.SchedulerProvider
import com.crazyworld.countries.view.countries.CountriesRepository
import com.crazyworld.countries.view.countries.CountriesRepositoryImpl
import com.crazyworld.countries.view.countries.CountriesViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val countriesModule = module {
    viewModel { CountriesViewModel(get(), get()) }
    single { CountriesRepositoryImpl(get()) as CountriesRepository }
}

val listModule = module {

}


val rxModule = module {
    single { ApplicationSchedulerProvider() as SchedulerProvider }
}

val modules = listOf(remoteDataSourceModel, rxModule, countriesModule, listModule)