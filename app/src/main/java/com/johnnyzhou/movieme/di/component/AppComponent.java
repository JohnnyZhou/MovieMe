package com.johnnyzhou.movieme.di.component;

import android.app.Application;

import com.johnnyzhou.movieme.MovieMeApplication;
import com.johnnyzhou.movieme.di.module.ApiModule;
import com.johnnyzhou.movieme.di.module.AppModule;
import com.johnnyzhou.movieme.network.NetworkManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import de.greenrobot.event.EventBus;
import rx.Scheduler;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
    void inject(MovieMeApplication application);

    NetworkManager networkManager();

    Application application();

    @Named(AppModule.MAIN_THREAD)
    Scheduler mainThread();

    @Named(AppModule.IO_THREAD)
    Scheduler ioThread();

    EventBus Bus();
}