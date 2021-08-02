package com.johnnyzhou.movieme.ui.person.list;

import com.johnnyzhou.movieme.network.response.PersonListResponse;
import com.johnnyzhou.movieme.ui.person.Person;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

//public class PersonListInteractor {
//    private NetworkManager movieDbApi;
//    private Scheduler ioThread;
//
//    @Inject
//    public PersonListInteractor(NetworkManager movieDbApi, Scheduler ioThread) {
//        this.movieDbApi = movieDbApi;
//        this.ioThread = ioThread;
//    }
//
//    public Observable<List<Person>> getPopularPeople() {
//        return movieDbApi.getPopularPeople()
//                .subscribeOn(ioThread)
//                .map(new Func1<PersonListResponse, List<Person>>() {
//                    @Override
//                    public List<Person> call(PersonListResponse personListResponse) {
//                        return personListResponse.getPeople();
//                    }
//                });
//    }
//
//    public Observable<List<Person>> searchPeople(String personName) {
//        return movieDbApi.searchPeople(personName)
//                .subscribeOn(ioThread)
//                .map(new Func1<PersonListResponse, List<Person>>() {
//                    @Override
//                    public List<Person> call(PersonListResponse personListResponse) {
//                        return personListResponse.getPeople();
//                    }
//                });
//    }
//}