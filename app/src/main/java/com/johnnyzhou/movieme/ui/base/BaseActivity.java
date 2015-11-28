package com.johnnyzhou.movieme.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.johnnyzhou.movieme.MovieMeApp;
import com.johnnyzhou.movieme.di.component.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {
    public AppComponent getApplicationComponent() {
        return ((MovieMeApp) getApplication()).getAppComponent();
    }
}
