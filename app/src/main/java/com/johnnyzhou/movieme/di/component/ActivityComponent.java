package com.johnnyzhou.movieme.di.component;

import com.johnnyzhou.movieme.di.module.ActivityModule;
import com.johnnyzhou.movieme.di.scope.PerActivity;
import com.johnnyzhou.movieme.ui.MainActivity;
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetailActivity;
import com.johnnyzhou.movieme.ui.person.detail.PersonDetailActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);

    void inject(MovieDetailActivity activity);

    void inject(PersonDetailActivity activity);
}
