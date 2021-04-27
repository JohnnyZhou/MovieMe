package com.johnnyzhou.movieme.ui.movie.detail;

import com.johnnyzhou.movieme.ui.common.MvpPresenter;
import com.johnnyzhou.movieme.ui.common.MvpView;

public interface MovieDetailContract {
    interface View extends MvpView {
        void showMovie(MovieDetail movie);

        void showLoading(boolean show);

        void showError(String errorMessage);
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        void getMovie(String movieId);
    }
}
