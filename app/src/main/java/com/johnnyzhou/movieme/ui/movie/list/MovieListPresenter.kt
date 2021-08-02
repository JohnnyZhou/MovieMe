package com.johnnyzhou.movieme.ui.movie.list

import com.johnnyzhou.movieme.di.module.AppModule
import com.johnnyzhou.movieme.ui.movie.Movie
import com.johnnyzhou.movieme.util.NetworkUtil
import rx.Scheduler
import rx.functions.Action1
import rx.subscriptions.CompositeSubscription
import java.util.*
import javax.inject.Inject
import javax.inject.Named

//class MovieListPresenter @Inject constructor(
//    interactor: MovieListInteractor,
//    @Named(AppModule.MAIN_THREAD) mainThread: Scheduler
//) : MovieListContract.Presenter<MovieListContract.View?> {
//    private val currentQueryMap: MutableMap<String, List<Movie>>
//    private val interactor: MovieListInteractor
//    private var subscriptions: CompositeSubscription
//    private var view: MovieListContract.View
//    private var popularMovies: List<Movie>? = null
//    private val mainThread: Scheduler
//    override fun searchMovie(query: String) {
//        showLoading()
//        if (currentQueryMap.containsKey(query)) {
//            showMovies(currentQueryMap[query]!!)
//            return
//        }
//        currentQueryMap.clear()
//        interactor.searchMovies(query)
//            .observeOn(mainThread)
//            .subscribe({ searchResult ->
//                currentQueryMap[query] = searchResult
//                showMovies(searchResult)
//            }) { showError(NetworkUtil.NETWORK_ERROR_MSG) }
//    }
//
//    override fun getPopularMovies() {
//        showLoading()
//        if (popularMovies != null) {
//            showMovies(popularMovies!!)
//            return
//        }
//        val onNext =
//            Action1<List<Movie>> { movies ->
//                popularMovies = movies
//                showMovies(movies)
//            }
//        val onError: Action1<Throwable> = Action1 { showError(NetworkUtil.NETWORK_ERROR_MSG) }
//        subscriptions.add(
//            interactor.popularMovies
//                .observeOn(mainThread)
//                .subscribe(onNext, onError)
//        )
//    }
//
//    private fun showMovies(movies: List<Movie>) {
//        view.showLoading(false)
//        view.showMovies(movies)
//    }
//
//    private fun showLoading() {
//        view.showError(null)
//        view.showLoading(true)
//    }
//
//    private fun showError(errorMsg: String) {
//        view.showLoading(false)
//        view.showError(errorMsg)
//    }
//
//    override fun bindView(view: MovieListContract.View) {
//        if (subscriptions.isUnsubscribed) subscriptions = CompositeSubscription()
//        this.view = view
//    }
//
//    override fun unbindView() {
//        view = emptyView
//    }
//
//    override fun onDestroy() {
//        subscriptions.clear()
//    }
//
//    companion object {
//        private val emptyView: MovieListContract.View = object : MovieListContract.View {
//            override fun showMovies(movies: List<Movie>) { // Do nothing
//            }
//
//            override fun showLoading(show: Boolean) {
//                // Do nothing
//            }
//
//            override fun showError(message: String) {
//                // Do nothing
//            }
//        }
//    }
//
//    init {
//        subscriptions = CompositeSubscription()
//        this.interactor = interactor
//        this.mainThread = mainThread
//        currentQueryMap = HashMap()
//        view = emptyView
//    }
//}