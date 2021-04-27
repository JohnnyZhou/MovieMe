package com.johnnyzhou.movieme.network;

import com.johnnyzhou.movieme.network.response.MovieListResponse;
import com.johnnyzhou.movieme.network.response.PersonListResponse;
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetail;
import com.johnnyzhou.movieme.ui.person.detail.PersonDetail;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface MovieService {
    @GET("discover/movie?sort_by=popularity.desc")
    Observable<MovieListResponse> popularMovies();

    @GET("person/popular?sort_by=popularity.desc")
    Observable<PersonListResponse> popularPeople();

    @GET("search/movie?")
    Observable<MovieListResponse> searchMovie(
            @Query("query") String movieName
    );

    @GET("search/person?")
    Observable<PersonListResponse> searchPerson(
            @Query("query") String personName
    );

    @GET("movie/{id}?")
    Observable<MovieDetail> getMovie(
            @Path("id") String movieId
    );

    @GET("person/{id}?")
    Observable<PersonDetail> getPerson(
            @Path("id") String personId
    );
}