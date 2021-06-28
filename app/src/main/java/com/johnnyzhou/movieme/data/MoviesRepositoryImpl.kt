package com.johnnyzhou.movieme.data

import com.johnnyzhou.movieme.network.MovieService

class MoviesRepositoryImpl(private val movieService: MovieService) : MoviesRepository {

    // when to use suspend?
    suspend fun getPopularMovies() {
        movieService.getPopularMovies()
    }
}