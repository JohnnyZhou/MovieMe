package com.johnnyzhou.movieme.ui.person.detail;

import com.johnnyzhou.movieme.util.NetworkUtil;

import rx.Scheduler;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class PersonDetailPresenter implements PersonDetailContract.Presenter<PersonDetailContract.View> {
    private PersonDetailInteractor interactor;
    private CompositeSubscription subscriptions;
    private PersonDetailContract.View view;
    private Scheduler mainThread;
    private DetailPerson person;

    private static PersonDetailContract.View emptyView = new PersonDetailContract.View() {
        @Override
        public void showPerson(DetailPerson person) {

        }

        @Override
        public void showLoading(boolean show) {

        }

        @Override
        public void showError(String errorMessage) {

        }
    };

    public PersonDetailPresenter(PersonDetailInteractor interactor, Scheduler mainThread) {
        subscriptions = new CompositeSubscription();
        this.interactor = interactor;
        this.mainThread = mainThread;
        view = emptyView;
    }

    @Override
    public void getPerson(String personId) {
        showLoading();

        if (person != null) {
            showPerson(person);
            return;
        }

        interactor.getPerson(personId)
                .observeOn(mainThread)
                .subscribe(new Action1<DetailPerson>() {
                    @Override
                    public void call(DetailPerson person) {
                        PersonDetailPresenter.this.person = person;
                        showPerson(person);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        showError(NetworkUtil.NETWORK_ERROR_MSG);
                    }
                });
    }

    private void showPerson(DetailPerson person) {
        view.showError(null);
        view.showLoading(false);
        view.showPerson(person);
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
    public void bindView(PersonDetailContract.View view) {
        this.view = view;

        if (subscriptions.isUnsubscribed())
            subscriptions = new CompositeSubscription();
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