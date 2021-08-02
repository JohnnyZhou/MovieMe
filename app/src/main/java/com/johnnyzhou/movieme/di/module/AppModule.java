package com.johnnyzhou.movieme.di.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.johnnyzhou.movieme.MovieMeApplication;

import java.util.Calendar;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class AppModule {
    public static final String MAIN_THREAD = "mainThread";
    public static final String IO_THREAD = "ioThread";
    private final Application movieMeApp;

    public AppModule(MovieMeApplication movieMeApp) {
        this.movieMeApp = movieMeApp;
    }

    @Singleton
    @Provides
    SharedPreferences provideDefaultSharedPreferences(Application movieMeApp) {
        return PreferenceManager.getDefaultSharedPreferences(movieMeApp);
    }

//    @Singleton
//    @Provides
//    EventBus provideEventBus() {
//        return EventBus.getDefault();
//    }

    @Singleton
    @Provides
    Application provideApplicationContext() {
        return movieMeApp;
    }

    @Singleton
    @Provides
    @Named(MAIN_THREAD)
    Scheduler provideMainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Singleton
    @Provides
    @Named(IO_THREAD)
    Scheduler provideIoThread() {
        return Schedulers.io();
    }

    @Singleton
    @Provides
    Calendar provideCalendar() {
        return Calendar.getInstance();
    }
}