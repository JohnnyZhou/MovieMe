package com.johnnyzhou.movieme.network;

import com.johnnyzhou.movieme.network.response.MovieListResponse;
import com.johnnyzhou.movieme.network.response.PersonListResponse;
import com.johnnyzhou.movieme.ui.movie.detail.DetailMovie;
import com.johnnyzhou.movieme.ui.person.detail.DetailPerson;

import javax.inject.Inject;

import rx.Observable;

public class NetworkManagerImpl implements NetworkManager {
    private MovieApi movieApi;

    @Inject
    public NetworkManagerImpl(MovieApi movieApi) {
        this.movieApi = movieApi;
    }

    @Override
    public Observable<MovieListResponse> getPopularMovies() {
        return movieApi.getPopularMovies();
    }

    @Override
    public Observable<DetailMovie> getMovie(String movieId) {
        return movieApi.getMovie(movieId);
    }

    @Override
    public Observable<MovieListResponse> searchMovies(String movieName) {
        return movieApi.searchMovie(movieName);
    }

    @Override
    public Observable<PersonListResponse> getPopularPeople() {
        return movieApi.getPopularPeople();
    }

    @Override
    public Observable<DetailPerson> getPerson(String personId) {
        return movieApi.getPerson(personId);
    }

    @Override
    public Observable<PersonListResponse> searchPeople(String personName) {
        return movieApi.searchPerson(personName);
    }
}