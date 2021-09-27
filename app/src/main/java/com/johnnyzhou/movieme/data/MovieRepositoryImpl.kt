package com.johnnyzhou.movieme.data

import com.johnnyzhou.movieme.network.MovieService
import com.johnnyzhou.movieme.ui.movie.Movie
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetail
import com.johnnyzhou.movieme.ui.person.Person
import com.johnnyzhou.movieme.ui.person.detail.PersonDetail
import javax.inject.Inject
import javax.inject.Singleton

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return movieService.getPopularMovies().movies.orEmpty()
    }

    override suspend fun searchMovie(movieName: String): List<Movie> {
        return movieService.getMovieByQuery(movieName).movies.orEmpty()
    }

    override suspend fun getPopularPeople(): List<Person> {
        return movieService.getPopularPeople().people.orEmpty()
    }

    override suspend fun searchPerson(personName: String): List<Person> {
        return movieService.getPersonByQuery(personName).people.orEmpty()
    }

    override suspend fun getMovie(movieId: String): MovieDetail {
        return movieService.getMovie(movieId)
    }

    override suspend fun getPerson(personId: String): PersonDetail {
        return movieService.getPerson(personId)
    }
}