package com.johnnyzhou.movieme.ui.movie.detail;

import com.johnnyzhou.movieme.di.module.AppModule;
import com.johnnyzhou.movieme.util.NetworkUtil;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Scheduler;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MovieDetailPresenter implements MovieDetailContract.Presenter<MovieDetailContract.View> {
    private CompositeSubscription subscriptions;
    private MovieDetailInteractor interactor;
    private MovieDetailContract.View view;
    private Scheduler mainThread;
    private DetailMovie movie;

    private static MovieDetailContract.View emptyView = new MovieDetailContract.View() {
        @Override
        public void showMovie(DetailMovie movie) {

        }

        @Override
        public void showLoading(boolean show) {

        }

        @Override
        public void showError(String errorMessage) {

        }
    };

    @Inject
    public MovieDetailPresenter(MovieDetailInteractor interactor,
                                @Named(AppModule.MAIN_THREAD) Scheduler mainThread) {

        subscriptions = new CompositeSubscription();
        this.interactor = interactor;
        this.mainThread = mainThread;
        view = emptyView;
    }

    @Override
    public void getMovie(String movieId) {
        showLoading();
        if (movie != null) {
            showMovie(movie);
            return;
        }

        interactor.getMovie(movieId)
                .observeOn(mainThread)
                .subscribe(new Action1<DetailMovie>() {
                    @Override
                    public void call(DetailMovie movie) {
                        MovieDetailPresenter.this.movie = movie;
                        showMovie(movie);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        showError(NetworkUtil.NETWORK_ERROR_MSG);
                    }
                });
    }

    private void showMovie(DetailMovie movie) {
        view.showError(null);
        view.showLoading(false);
        view.showMovie(movie);
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
    public void bindView(MovieDetailContract.View view) {
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
        subscriptions.unsubscribe();
    }
}