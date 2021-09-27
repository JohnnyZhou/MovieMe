package com.johnnyzhou.movieme.di.module

import androidx.lifecycle.ViewModel
import com.johnnyzhou.movieme.business.GetMovieUseCase
import com.johnnyzhou.movieme.business.GetMovieUserCaseImpl
import com.johnnyzhou.movieme.data.MovieRepository
import com.johnnyzhou.movieme.data.MovieRepositoryImpl
import com.johnnyzhou.movieme.ui.movie.list.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class FragmentBindingModule {
    @Binds
    abstract fun bindGetMovieUseCase(useCase: GetMovieUserCaseImpl): GetMovieUseCase

    @Binds
    abstract fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository

//    @Binds
//    fun bindMovieListViewModel(viewModel: MovieListViewModel): ViewModel // shouldn't be creating it here
}