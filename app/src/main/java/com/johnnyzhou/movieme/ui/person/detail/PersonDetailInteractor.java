package com.johnnyzhou.movieme.ui.person.detail;

import com.johnnyzhou.movieme.network.NetworkManager;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

public class PersonDetailInteractor {
    private NetworkManager movieDbApi;
    private Scheduler ioThread;

    @Inject
    public PersonDetailInteractor(NetworkManager movieDbApi, Scheduler ioThread) {
        this.movieDbApi = movieDbApi;
        this.ioThread = ioThread;
    }

    public Observable<DetailPerson> getPerson(String personId) {
        return movieDbApi.getPerson(personId)
                .subscribeOn(ioThread);
    }
}
