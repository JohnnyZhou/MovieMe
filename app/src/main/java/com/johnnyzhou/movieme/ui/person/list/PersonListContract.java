package com.johnnyzhou.movieme.ui.person.list;

import com.johnnyzhou.movieme.ui.common.MvpPresenter;
import com.johnnyzhou.movieme.ui.common.MvpView;
import com.johnnyzhou.movieme.ui.person.Person;

import java.util.List;

import rx.Observable;

public interface PersonListContract {
    interface View extends MvpView {
        void showPeople(List<Person> people);

        void showLoading(boolean show);

        void showError(String message);
    }

    interface Presenter<T extends MvpView> extends MvpPresenter<T> {
        void getPopularPeople();

        void searchPeople(String personName);
    }

    interface Interactor {
        Observable<List<Person>> getPopularPeople();

        Observable<List<Person>> searchPeople(String personName);
    }
}
