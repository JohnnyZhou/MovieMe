package com.johnnyzhou.movieme.ui.movie.detail;

import com.johnnyzhou.movieme.network.NetworkManager;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

public class MovieDetailInteractor {
    private NetworkManager networkManager;
    private Scheduler ioThread;

    @Inject
    public MovieDetailInteractor(NetworkManager networkManager, Scheduler ioThread) {
        this.networkManager = networkManager;
        this.ioThread = ioThread;
    }

    public Observable<MovieDetail> getMovie(String movieId) {
        return networkManager.getMovie(movieId)
                .subscribeOn(ioThread);
    }
}
