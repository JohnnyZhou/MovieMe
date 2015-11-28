package com.johnnyzhou.movieme.di.module;

import com.johnnyzhou.movieme.di.scope.PerFragment;
import com.johnnyzhou.movieme.network.NetworkManager;
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetailContract;
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetailInteractor;
import com.johnnyzhou.movieme.ui.movie.detail.MovieDetailPresenter;
import com.johnnyzhou.movieme.ui.movie.list.MovieListContract;
import com.johnnyzhou.movieme.ui.movie.list.MovieListInteractor;
import com.johnnyzhou.movieme.ui.movie.list.MovieListPresenter;
import com.johnnyzhou.movieme.ui.person.detail.PersonDetailContract;
import com.johnnyzhou.movieme.ui.person.detail.PersonDetailInteractor;
import com.johnnyzhou.movieme.ui.person.detail.PersonDetailPresenter;
import com.johnnyzhou.movieme.ui.person.list.PersonListContract;
import com.johnnyzhou.movieme.ui.person.list.PersonListInteractor;
import com.johnnyzhou.movieme.ui.person.list.PersonListPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module
public class FragmentModule {
    @PerFragment
    @Provides
    MovieListContract.Presenter<MovieListContract.View> provideMovieListPresenter(MovieListInteractor interactor,
                                                                                  @Named(AppModule.MAIN_THREAD) Scheduler mainThread) {

        return new MovieListPresenter(interactor, mainThread);
    }

    @PerFragment
    @Provides
    MovieDetailContract.Presenter<MovieDetailContract.View> provideMovieDetailPresenter(MovieDetailInteractor interactor,
                                                                                        @Named(AppModule.MAIN_THREAD) Scheduler mainThread) {

        return new MovieDetailPresenter(interactor, mainThread);
    }

    @PerFragment
    @Provides
    PersonListContract.Presenter<PersonListContract.View> providePersonListPresenter(PersonListInteractor interactor,
                                                                                     @Named(AppModule.MAIN_THREAD) Scheduler mainThread) {

        return new PersonListPresenter(interactor, mainThread);
    }

    @PerFragment
    @Provides
    PersonDetailContract.Presenter<PersonDetailContract.View> providePersonDetailPresenter(PersonDetailInteractor interactor,
                                                                                           @Named(AppModule.MAIN_THREAD) Scheduler mainThread) {

        return new PersonDetailPresenter(interactor, mainThread);
    }

    @PerFragment
    @Provides
    MovieListInteractor provideMovieListInteractor(NetworkManager networkManager,
                                                            @Named(AppModule.IO_THREAD) Scheduler ioThread) {

        return new MovieListInteractor(networkManager, ioThread);
    }

    @PerFragment
    @Provides
    MovieDetailInteractor provideMovieDetailInteractor(NetworkManager networkManager,
                                                                @Named(AppModule.IO_THREAD) Scheduler ioThread) {

        return new MovieDetailInteractor(networkManager, ioThread);
    }

    @PerFragment
    @Provides
    PersonListInteractor providePersonListInteractor(NetworkManager networkManager,
                                                              @Named(AppModule.IO_THREAD) Scheduler ioThread) {

        return new PersonListInteractor(networkManager, ioThread);
    }

    @PerFragment
    @Provides
    PersonDetailInteractor providePersonDetailInteractor(NetworkManager networkManager,
                                                                  @Named(AppModule.IO_THREAD) Scheduler ioThread) {

        return new PersonDetailInteractor(networkManager, ioThread);
    }
}