package com.johnnyzhou.movieme.network

import com.johnnyzhou.movieme.network.response.MovieListResponse
import com.johnnyzhou.movieme.network.response.PersonListResponse
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetail
import com.johnnyzhou.movieme.ui.person.detail.PersonDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getPopularMovies(): MovieListResponse // TODO, here too

    @GET("person/popular?sort_by=popularity.desc")
    suspend fun getPopularPeople(): PersonListResponse // TODO, should this be nullable?

    @GET("search/movie?")
    suspend fun getMovieByQuery(@Query("query") movieName: String?): MovieListResponse

    @GET("search/person?")
    suspend fun getPersonByQuery(@Query("query") personName: String?): PersonListResponse

    @GET("movie/{id}?")
    suspend fun getMovie(@Path("id") movieId: String?): MovieDetail

    @GET("person/{id}?")
    suspend fun getPerson(@Path("id") personId: String?): PersonDetail
}