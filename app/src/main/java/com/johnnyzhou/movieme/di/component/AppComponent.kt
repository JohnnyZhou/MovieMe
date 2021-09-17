package com.johnnyzhou.movieme.di.component

import android.app.Application
import com.johnnyzhou.movieme.MovieMeApplication
import com.johnnyzhou.movieme.business.DateProvider
import com.johnnyzhou.movieme.di.module.ApiModule
import com.johnnyzhou.movieme.di.module.AppModule
import com.johnnyzhou.movieme.network.MovieService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {
    fun inject(application: MovieMeApplication)

    fun application(): Application

    val movieService: MovieService

    val dateProvider: DateProvider
}