package com.johnnyzhou.movieme.network;

import com.johnnyzhou.movieme.network.response.MovieListResponse;
import com.johnnyzhou.movieme.network.response.PersonListResponse;
import com.johnnyzhou.movieme.ui.movie.detail.DetailMovie;
import com.johnnyzhou.movieme.ui.person.detail.DetailPerson;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class MovieApi {
    private MovieService movieService;

    @Inject
    public MovieApi(MovieService movieService) {
        this.movieService = movieService;
    }

    public Observable<MovieListResponse> getPopularMovies() {
        return movieService.popularMovies();
    }

    public Observable<MovieListResponse> searchMovie(String movieName) {
        return movieService.searchMovie(movieName);
    }

    public Observable<PersonListResponse> getPopularPeople() {
        return movieService.popularPeople();
    }

    public Observable<PersonListResponse> searchPerson(String personName) {
        return movieService.searchPerson(personName);
    }

    public Observable<DetailMovie> getMovie(String movieId) {
        return movieService.getMovie(movieId);
    }

    public Observable<DetailPerson> getPerson(String personId) {
        return movieService.getPerson(personId);
    }
}