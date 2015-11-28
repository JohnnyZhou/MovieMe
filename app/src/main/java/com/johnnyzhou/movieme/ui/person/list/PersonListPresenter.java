package com.johnnyzhou.movieme.ui.person.list;

import com.johnnyzhou.movieme.di.module.AppModule;
import com.johnnyzhou.movieme.ui.person.Person;
import com.johnnyzhou.movieme.util.NetworkUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Scheduler;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class PersonListPresenter implements PersonListContract.Presenter<PersonListContract.View> {
    private Map<String, List<Person>> currentQueryMap;
    private PersonListInteractor interactor;
    private CompositeSubscription subscriptions;
    private PersonListContract.View view;
    private List<Person> popularPeople;
    private Scheduler mainThread;

    private static PersonListContract.View emptyView = new PersonListContract.View() {
        @Override
        public void showPeople(List<Person> people) {

        }

        @Override
        public void showLoading(boolean show) {

        }

        @Override
        public void showError(String message) {

        }
    };

    @Inject
    public PersonListPresenter(PersonListInteractor interactor,
                               @Named(AppModule.MAIN_THREAD) Scheduler mainThread) {

        subscriptions = new CompositeSubscription();
        this.interactor = interactor;
        this.mainThread = mainThread;
        currentQueryMap = new HashMap<>();
        view = emptyView;
    }

    @Override
    public void getPopularPeople() {
        showLoading();

        if (popularPeople != null) {
            showPeople(popularPeople);
            return;
        }

        interactor.getPopularPeople()
                .observeOn(mainThread)
                .subscribe(new Action1<List<Person>>() {
                    @Override
                    public void call(List<Person> people) {
                        popularPeople = people;
                        showPeople(people);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        showError(NetworkUtil.NETWORK_ERROR_MSG);
                    }
                });
    }

    @Override
    public void searchPeople(final String query) {
        showLoading();

        if (currentQueryMap.containsKey(query)) {
            showPeople(currentQueryMap.get(query));
            return;
        }

        interactor.searchPeople(query)
                .observeOn(mainThread)
                .subscribe(new Action1<List<Person>>() {
                    @Override
                    public void call(List<Person> searchResult) {
                        currentQueryMap.put(query, searchResult);
                        showPeople(searchResult);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        showError(NetworkUtil.NETWORK_ERROR_MSG);
                    }
                });
    }

    private void showPeople(List<Person> people) {
        view.showLoading(false);
        view.showPeople(people);
    }

    private void showLoading() {
        view.showError(null);
        view.showLoading(true);
    }

    private void showError(String errorMsg) {
        view.showLoading(false);
        view.showError(errorMsg);
    }

    @Override
    public void bindView(PersonListContract.View view) {
        if (subscriptions.isUnsubscribed())
            subscriptions = new CompositeSubscription();

        this.view = view;
    }

    @Override
    public void unbindView() {
        view = emptyView;
    }

    @Override
    public void onDestroy() {
        subscriptions.clear();
    }
}