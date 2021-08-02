package com.johnnyzhou.movieme.di.component;

import com.johnnyzhou.movieme.di.module.FragmentModule;
import com.johnnyzhou.movieme.di.scope.PerFragment;
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetailFragment;
import com.johnnyzhou.movieme.ui.movie.list.MovieListFragment;
import com.johnnyzhou.movieme.ui.person.detail.PersonDetailFragment;
import com.johnnyzhou.movieme.ui.person.list.PersonListFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(MovieListFragment fragment);

//    void inject(MovieDetailFragment fragment);

    void inject(PersonListFragment fragment);

//    void inject(PersonDetailFragment fragment);
}