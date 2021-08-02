package com.johnnyzhou.movieme.di.module;

import com.johnnyzhou.movieme.di.scope.PerFragment;
import com.johnnyzhou.movieme.ui.movie.list.MovieListContract;
import com.johnnyzhou.movieme.ui.person.detail.PersonDetailContract;
import com.johnnyzhou.movieme.ui.person.detail.PersonDetailPresenter;
import com.johnnyzhou.movieme.ui.person.list.PersonListContract;
import com.johnnyzhou.movieme.ui.person.list.PersonListPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module
public class FragmentModule {
//    @PerFragment
//    @Provides
//    MovieListContract.Presenter<MovieListContract.View> provideMovieListPresenter(MovieListInteractor interactor,
//                                                                                  @Named(AppModule.MAIN_THREAD) Scheduler mainThread) {
//        return new MovieListPresenter(interactor, mainThread);
//    }
//
//    @PerFragment
//    @Provides
//    PersonListContract.Presenter<PersonListContract.View> providePersonListPresenter(PersonListInteractor interactor,
//                                                                                     @Named(AppModule.MAIN_THREAD) Scheduler mainThread) {
//        return new PersonListPresenter(interactor, mainThread);
//    }
//
//    @PerFragment
//    @Provides
//    PersonDetailContract.Presenter<PersonDetailContract.View> providePersonDetailPresenter(PersonDetailInteractor interactor,
//                                                                                           @Named(AppModule.MAIN_THREAD) Scheduler mainThread) {
//        return new PersonDetailPresenter(interactor, mainThread);
//    }
}