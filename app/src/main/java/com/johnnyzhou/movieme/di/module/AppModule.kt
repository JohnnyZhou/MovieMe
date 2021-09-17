package com.johnnyzhou.movieme.di.module

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.johnnyzhou.movieme.MovieMeApplication
import com.johnnyzhou.movieme.business.DateProvider
import com.johnnyzhou.movieme.business.DateProviderImpl
import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val movieMeApp: MovieMeApplication) {

    @Singleton
    @Provides
    fun provideDefaultSharedPreferences(movieMeApp: Application?): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(movieMeApp)
    }

    @Singleton
    @Provides
    fun provideApplicationContext(): Application {
        return movieMeApp
    }

    @Singleton
    @Provides
    @Named(MAIN_THREAD)
    fun provideMainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Singleton
    @Provides
    @Named(IO_THREAD)
    fun provideIoThread(): Scheduler {
        return Schedulers.io()
    }

    @Singleton
    @Provides
    fun provideCalendar(): Calendar {
        return Calendar.getInstance()
    }

    @Singleton
    @Provides
    fun provideDateProvider(): DateProvider = DateProviderImpl()

    companion object {
        const val MAIN_THREAD = "mainThread"
        const val IO_THREAD = "ioThread"
    }
}