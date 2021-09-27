package com.johnnyzhou.movieme

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class MovieMeApplication : Application() {
//    val appComponent: AppComponent by lazy {
//        DaggerAppComponent.builder()
//            .appModule(AppModule(this))
//            .apiModule(ApiModule(NetworkUtil.API_URL))
//            .build()
//    }

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

