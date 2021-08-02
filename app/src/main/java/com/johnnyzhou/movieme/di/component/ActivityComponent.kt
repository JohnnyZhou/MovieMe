package com.johnnyzhou.movieme.di.component

import com.johnnyzhou.movieme.di.module.ActivityModule
import com.johnnyzhou.movieme.di.scope.PerActivity
import com.johnnyzhou.movieme.ui.MainActivity
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetailActivity
import com.johnnyzhou.movieme.ui.person.detail.PersonDetailActivity
import dagger.Component

@PerActivity
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: MovieDetailActivity)
    fun inject(activity: PersonDetailActivity)
}
