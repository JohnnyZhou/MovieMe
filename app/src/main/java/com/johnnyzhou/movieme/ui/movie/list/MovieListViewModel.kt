package com.johnnyzhou.movieme.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnnyzhou.movieme.business.GetMovieUseCase
import com.johnnyzhou.movieme.business.GetMovieUserCaseImpl
import com.johnnyzhou.movieme.ui.movie.Movie
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    sealed class UiState {
        object Error : UiState()
        object Loading : UiState()
        object Success : UiState()
    }

    private val mutableUiState = MutableLiveData<UiState>()
    private val mutableMovieLiveData = MutableLiveData<List<Movie>>()

    val uiState: LiveData<UiState> = mutableUiState
    val movieList: LiveData<List<Movie>> = mutableMovieLiveData


    lateinit var getMovieUseCase: GetMovieUseCase

    // TODO check screen re-configuration
    fun getMovieList() {
        mutableUiState.value = UiState.Loading
        viewModelScope.launch {
//            val movies = getMovieUseCase.getPopularMovies()
//            mutableMovieLiveData.postValue(movies)
        }
    }

    fun searchMovie(query: String?) {

    }

    fun getPopularMovies() {

    }
}