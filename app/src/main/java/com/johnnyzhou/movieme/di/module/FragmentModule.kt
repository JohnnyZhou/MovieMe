package com.johnnyzhou.movieme.di.module

import androidx.lifecycle.ViewModelProvider
import com.johnnyzhou.movieme.business.GetMovieUseCase
import com.johnnyzhou.movieme.ui.movie.list.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {
    @Provides
    fun provideMovieListViewModelFactory(useCase: GetMovieUseCase): ViewModelProvider.Factory {
        return ViewModelFactory(useCase)
    }
}