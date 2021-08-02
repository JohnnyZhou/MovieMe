package com.johnnyzhou.movieme.di.module

import com.johnnyzhou.movieme.business.GetMovieUseCase
import com.johnnyzhou.movieme.business.GetMovieUserCaseImpl
import com.johnnyzhou.movieme.data.MovieRepository
import com.johnnyzhou.movieme.data.MovieRepositoryImpl
import com.johnnyzhou.movieme.network.MovieService
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {
    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMovieUseCase {
        return GetMovieUserCaseImpl(movieRepository)
    }

    @Provides
    fun provideMovieRepository(movieService: MovieService): MovieRepository {
        return MovieRepositoryImpl(movieService)
    }
}