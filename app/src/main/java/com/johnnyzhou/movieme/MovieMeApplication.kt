package com.johnnyzhou.movieme

import android.app.Application
import com.johnnyzhou.movieme.di.component.AppComponent
import com.johnnyzhou.movieme.di.component.DaggerAppComponent
import com.johnnyzhou.movieme.di.module.ApiModule
import com.johnnyzhou.movieme.di.module.AppModule
import com.johnnyzhou.movieme.util.NetworkUtil
import timber.log.Timber
import timber.log.Timber.DebugTree

class MovieMeApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .apiModule(ApiModule(NetworkUtil.API_URL))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}

