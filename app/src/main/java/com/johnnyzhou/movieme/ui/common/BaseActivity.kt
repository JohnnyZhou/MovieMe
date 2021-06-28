package com.johnnyzhou.movieme.ui.common

import androidx.appcompat.app.AppCompatActivity
import com.johnnyzhou.movieme.MovieMeApplication
import com.johnnyzhou.movieme.di.component.AppComponent

abstract class BaseActivity : AppCompatActivity() {
    val applicationComponent: AppComponent
        get() = (application as MovieMeApplication).appComponent
}