package com.johnnyzhou.movieme.di.module

import androidx.fragment.app.FragmentManager
import com.johnnyzhou.movieme.di.scope.PerActivity
import com.johnnyzhou.movieme.ui.common.BaseActivity
import com.johnnyzhou.movieme.ui.movie.list.MovieListFragment
import com.johnnyzhou.movieme.ui.person.list.PersonListFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @PerActivity
    @Provides
    fun provideMovieListFragment(): MovieListFragment {
        return MovieListFragment.newInstance()
    }

    @PerActivity
    @Provides
    fun providePersonListFragment(): PersonListFragment {
        return PersonListFragment.newInstance()
    }
}