package com.crazyworld.countries.di

import com.crazyworld.countries.data.remote.RemoteDataSource
import com.crazyworld.countries.di.RemoteProperties.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RemoteProperties {
    const val BASE_URL = "https://restcountries.eu/"
}

val remoteDataSourceModel = module {
    single { createOkHttpClient() }
    single { createWebService<RemoteDataSource>(get(), BASE_URL) }
}

fun createOkHttpClient(): OkHttpClient {
    val logsInterceptor = HttpLoggingInterceptor()
    logsInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(logsInterceptor)
            .build()
}


inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String) : T {
    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    return retrofit.create(T::class.java)
}
