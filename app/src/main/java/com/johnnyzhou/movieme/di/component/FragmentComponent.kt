package com.johnnyzhou.movieme.di.component

import com.johnnyzhou.movieme.business.GetMovieUseCase
import com.johnnyzhou.movieme.di.module.FragmentModule
import com.johnnyzhou.movieme.di.scope.PerFragment
import com.johnnyzhou.movieme.ui.movie.list.MovieListFragment
import com.johnnyzhou.movieme.ui.person.list.PersonListFragment
import dagger.Component

@PerFragment
@Component(dependencies = [AppComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(fragment: MovieListFragment?)

    fun inject(fragment: PersonListFragment?)

    var getMovieUserCase: GetMovieUseCase
}