package com.johnnyzhou.movieme.ui.movie.list;

import com.johnnyzhou.movieme.ui.common.MvpPresenter;
import com.johnnyzhou.movieme.ui.common.MvpView;
import com.johnnyzhou.movieme.ui.movie.Movie;

import java.util.List;

public interface MovieListContract {
    interface View extends MvpView {
        void showMovies(List<Movie> movies);

        void showLoading(boolean show);

        void showError(String message);
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        void getPopularMovies();

        void searchMovie(String movieName);
    }
}
