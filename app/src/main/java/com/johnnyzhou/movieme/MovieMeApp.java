package com.johnnyzhou.movieme;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.johnnyzhou.movieme.di.component.AppComponent;
import com.johnnyzhou.movieme.di.component.DaggerAppComponent;
import com.johnnyzhou.movieme.di.module.ApiModule;
import com.johnnyzhou.movieme.di.module.AppModule;
import com.johnnyzhou.movieme.util.NetworkUtil;
import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.ExcludedRefs;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

public class MovieMeApp extends Application {
    private AppComponent appComponent;

    public static RefWatcher getRefWatcher(Context context) {
        MovieMeApp application = (MovieMeApp) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        initialiseInjectors();
        refWatcher = RefWatcher.DISABLED;

        if (BuildConfig.DEBUG) {
            refWatcher = installLeakCanary();
            Stetho.initializeWithDefaults(this);
            Timber.plant(new Timber.DebugTree());
        }
    }

    protected RefWatcher installLeakCanary() {
        return LeakCanary.install(this);
    }

    private void initialiseInjectors() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(NetworkUtil.API_URL))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}