package com.johnnyzhou.movieme.ui.person.detail;

import com.johnnyzhou.movieme.ui.base.MvpPresenter;
import com.johnnyzhou.movieme.ui.base.MvpView;

import rx.Observable;

public interface PersonDetailContract {
    interface View extends MvpView {
        void showPerson(DetailPerson person);

        void showLoading(boolean show);

        void showError(String errorMessage);
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        void getPerson(String personId);
    }
}