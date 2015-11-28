package com.johnnyzhou.movieme.ui.movie.detail;

import com.johnnyzhou.movieme.ui.base.MvpPresenter;
import com.johnnyzhou.movieme.ui.base.MvpView;

import rx.Observable;

public interface MovieDetailContract {
    interface View extends MvpView {
        void showMovie(DetailMovie movie);

        void showLoading(boolean show);

        void showError(String errorMessage);
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        void getMovie(String movieId);
    }
}
