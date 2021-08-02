package com.johnnyzhou.movieme.business

import com.johnnyzhou.movieme.ui.movie.Movie
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetail

interface GetMovieUseCase {
    suspend fun getPopularMovies(): List<Movie>

    suspend fun getMovies(name: String): List<Movie>

    suspend fun getMovieById(id: String): MovieDetail
}