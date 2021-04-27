package com.johnnyzhou.movieme.ui.person.detail;

import com.johnnyzhou.movieme.ui.common.MvpPresenter;
import com.johnnyzhou.movieme.ui.common.MvpView;

public interface PersonDetailContract {
    interface View extends MvpView {
        void showPerson(PersonDetail person);

        void showLoading(boolean show);

        void showError(String errorMessage);
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        void getPerson(String personId);
    }
}