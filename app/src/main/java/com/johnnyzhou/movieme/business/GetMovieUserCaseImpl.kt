package com.johnnyzhou.movieme.business

import com.johnnyzhou.movieme.data.MovieRepository
import com.johnnyzhou.movieme.ui.movie.Movie
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetail
import javax.inject.Inject

class GetMovieUserCaseImpl @Inject constructor(private val repository: MovieRepository) : GetMovieUseCase {
    override suspend fun getPopularMovies(): List<Movie> {
        return repository.getPopularMovies()
    }

    override suspend fun getMovies(name: String): List<Movie> {
        return repository.searchMovie(name)
    }

    override suspend fun getMovieById(id: String): MovieDetail {
        return repository.getMovie(id)
    }
}