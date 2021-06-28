package com.johnnyzhou.movieme.ui.movie.list;

import com.johnnyzhou.movieme.di.module.AppModule;
import com.johnnyzhou.movieme.ui.movie.Movie;
import com.johnnyzhou.movieme.util.NetworkUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Scheduler;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MovieListPresenter implements MovieListContract.Presenter<MovieListContract.View> {
    private Map<String, List<Movie>> currentQueryMap;
    private MovieListInteractor interactor;
    private CompositeSubscription subscriptions;
    private MovieListContract.View view;
    private List<Movie> popularMovies;
    private Scheduler mainThread;

    private static MovieListContract.View emptyView = new MovieListContract.View() {
        @Override
        public void showMovies(List<Movie> movies) { // Do nothing
        }

        @Override
        public void showLoading(boolean show) {
            // Do nothing
        }

        @Override
        public void showError(String message) {
            // Do nothing
        }
    };

    @Inject
    public MovieListPresenter(MovieListInteractor interactor,
                              @Named(AppModule.MAIN_THREAD) Scheduler mainThread) {

        subscriptions = new CompositeSubscription();
        this.interactor = interactor;
        this.mainThread = mainThread;
        currentQueryMap = new HashMap<>();
        view = emptyView;
    }

    @Override
    public void searchMovie(final String query) {
        showLoading();

        if (currentQueryMap.containsKey(query)) {
            showMovies(currentQueryMap.get(query));
            return;
        }

        currentQueryMap.clear();
        interactor.searchMovies(query)
                .observeOn(mainThread)
                .subscribe(new Action1<List<Movie>>() {
                    @Override
                    public void call(List<Movie> searchResult) {
                        currentQueryMap.put(query, searchResult);
                        showMovies(searchResult);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        showError(NetworkUtil.NETWORK_ERROR_MSG);
                    }
                });
    }

    @Override
    public void getPopularMovies() {
        showLoading();

        if (popularMovies != null) {
            showMovies(popularMovies);
            return;
        }

        Action1<List<Movie>> onNext = new Action1<List<Movie>>() {
            @Override
            public void call(List<Movie> movies) {
                MovieListPresenter.this.popularMovies = movies;
                showMovies(movies);
            }
        };

        Action1<Throwable> onError = new Action1<Throwable>() {
            @Override
            public void call(Throwable e) {
                showError(NetworkUtil.NETWORK_ERROR_MSG);
            }
        };

        subscriptions.add(interactor.getPopularMovies()
                .observeOn(mainThread)
                .subscribe(onNext, onError));
    }

    private void showMovies(final List<Movie> movies) {
        view.showLoading(false);
        view.showMovies(movies);
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
    public void bindView(MovieListContract.View view) {
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