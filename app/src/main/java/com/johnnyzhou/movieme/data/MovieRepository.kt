package com.johnnyzhou.movieme.data

import com.johnnyzhou.movieme.ui.movie.Movie
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetail
import com.johnnyzhou.movieme.ui.person.Person
import com.johnnyzhou.movieme.ui.person.detail.PersonDetail

interface MovieRepository {
    suspend fun getPopularMovies(): List<Movie>

    suspend fun getPopularPeople(): List<Person>

    suspend fun searchMovie(movieName: String): List<Movie>

    suspend fun searchPerson(personName: String): List<Person>

    suspend fun getMovie(movieId: String): MovieDetail

    suspend fun getPerson(personId: String): PersonDetail
}