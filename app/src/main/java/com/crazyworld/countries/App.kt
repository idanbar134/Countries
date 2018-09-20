package com.crazyworld.countries

import android.app.Application
import com.crazyworld.countries.di.modules
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, modules)
    }
}