package com.johnnyzhou.movieme.network;

import com.johnnyzhou.movieme.network.response.MovieListResponse;
import com.johnnyzhou.movieme.network.response.PersonListResponse;
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetail;
import com.johnnyzhou.movieme.ui.person.detail.PersonDetail;

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
        return movieService.getPopularMovies();
    }

    public Observable<MovieListResponse> searchMovie(String movieName) {
        return movieService.getMovieByQuery(movieName);
    }

    public Observable<PersonListResponse> getPopularPeople() {
        return movieService.getPopularPeople();
    }

    public Observable<PersonListResponse> searchPerson(String personName) {
        return movieService.getPersonByQuery(personName);
    }

    public Observable<MovieDetail> getMovie(String movieId) {
        return movieService.getMovie(movieId);
    }

    public Observable<PersonDetail> getPerson(String personId) {
        return movieService.getPerson(personId);
    }
}