package com.johnnyzhou.movieme.network;

import com.johnnyzhou.movieme.network.response.MovieListResponse;
import com.johnnyzhou.movieme.network.response.PersonListResponse;
import com.johnnyzhou.movieme.ui.movie.detail.DetailMovie;
import com.johnnyzhou.movieme.ui.person.detail.DetailPerson;

import rx.Observable;

public interface NetworkManager {
    Observable<MovieListResponse> getPopularMovies();

    Observable<PersonListResponse> getPopularPeople();

    Observable<MovieListResponse> searchMovies(String movieName);

    Observable<PersonListResponse> searchPeople(String personName);

    Observable<DetailMovie> getMovie(String movieId);

    Observable<DetailPerson> getPerson(String personId);
}
