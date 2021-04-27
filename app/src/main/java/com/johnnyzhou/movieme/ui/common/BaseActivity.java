package com.johnnyzhou.movieme.ui.common;

import androidx.appcompat.app.AppCompatActivity;

import com.johnnyzhou.movieme.MovieMeApp;
import com.johnnyzhou.movieme.di.component.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {
    public AppComponent getApplicationComponent() {
        return ((MovieMeApp) getApplication()).getAppComponent();
    }
}
