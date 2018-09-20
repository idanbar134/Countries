package com.crazyworld.countries.di

import com.crazyworld.countries.common.ApplicationSchedulerProvider
import com.crazyworld.countries.common.SchedulerProvider
import org.koin.dsl.module.module

val countriesModule = module {


}

val listModule = module {

}


val rxModule = module {
    single { ApplicationSchedulerProvider() as SchedulerProvider }
}

val mySpecialWay = listOf(remoteDataSourceModel, rxModule, countriesModule, listModule)