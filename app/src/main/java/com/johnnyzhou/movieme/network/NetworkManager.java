package com.johnnyzhou.movieme.network;

import com.johnnyzhou.movieme.network.response.MovieListResponse;
import com.johnnyzhou.movieme.network.response.PersonListResponse;
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetail;
import com.johnnyzhou.movieme.ui.person.detail.PersonDetail;

import rx.Observable;

public interface NetworkManager {
    Observable<MovieListResponse> getPopularMovies();

    Observable<PersonListResponse> getPopularPeople();

    Observable<MovieListResponse> searchMovies(String movieName);

    Observable<PersonListResponse> searchPeople(String personName);

    Observable<MovieDetail> getMovie(String movieId);

    Observable<PersonDetail> getPerson(String personId);
}
