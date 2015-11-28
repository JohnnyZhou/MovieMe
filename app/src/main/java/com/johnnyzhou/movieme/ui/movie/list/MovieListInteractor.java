package com.johnnyzhou.movieme.ui.movie.list;

import com.johnnyzhou.movieme.di.module.AppModule;
import com.johnnyzhou.movieme.network.NetworkManager;
import com.johnnyzhou.movieme.network.response.MovieListResponse;
import com.johnnyzhou.movieme.ui.movie.Movie;

import java.util.List;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

public class MovieListInteractor {
    private NetworkManager movieApiManager;
    private Scheduler ioThread;

    public MovieListInteractor(NetworkManager movieApiManager,
                               @Named(AppModule.IO_THREAD) Scheduler ioThread) {

        this.movieApiManager = movieApiManager;
        this.ioThread = ioThread;
    }

    public Observable<List<Movie>> getPopularMovies() {
        return movieApiManager.getPopularMovies()
                .subscribeOn(ioThread)
                .map(new Func1<MovieListResponse, List<Movie>>() {
                    @Override
                    public List<Movie> call(MovieListResponse movieListResponse) {
                        return movieListResponse.getMovies();
                    }
                });
    }

    public Observable<List<Movie>> searchMovies(String movieName) {
        return movieApiManager.searchMovies(movieName)
                .subscribeOn(ioThread)
                .map(new Func1<MovieListResponse, List<Movie>>() {
                    @Override
                    public List<Movie> call(MovieListResponse movieListResponse) {
                        return movieListResponse.getMovies();
                    }
                });
    }
}