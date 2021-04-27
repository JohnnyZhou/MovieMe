package com.johnnyzhou.movieme.di.module;

import androidx.fragment.app.FragmentManager;

import com.johnnyzhou.movieme.di.scope.PerActivity;
import com.johnnyzhou.movieme.ui.common.BaseActivity;
import com.johnnyzhou.movieme.ui.movie.list.MovieListFragment;
import com.johnnyzhou.movieme.ui.person.list.PersonListFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    MovieListFragment provideMovieListFragment() {
        return MovieListFragment.newInstance();
    }

    @PerActivity
    @Provides
    PersonListFragment providePersonListFragment() {
        return PersonListFragment.newInstance();
    }

    @PerActivity
    @Provides
    FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @PerActivity
    @Provides
    BaseActivity provideActivity() {
        return activity;
    }
}