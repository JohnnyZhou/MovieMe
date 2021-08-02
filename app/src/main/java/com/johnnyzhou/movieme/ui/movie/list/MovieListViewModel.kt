package com.johnnyzhou.movieme.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnnyzhou.movieme.business.GetMovieUseCase
import com.johnnyzhou.movieme.ui.common.UiState
import com.johnnyzhou.movieme.ui.movie.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel : ViewModel() {
    private val mutableUiState = MutableLiveData<UiState>()
    private val mutableMovieLiveData = MutableLiveData<List<Movie>>()
    val uiState: LiveData<UiState> = mutableUiState
    val movieList: LiveData<List<Movie>> = mutableMovieLiveData

    lateinit var getMovieUseCase: GetMovieUseCase

    // TODO check screen re-configuration
    fun getMovieList() {
        mutableUiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val movies = getMovieUseCase.getPopularMovies()
                mutableUiState.postValue(UiState.Success)
                mutableMovieLiveData.postValue(movies)
            } catch (e: Exception) {
                e.printStackTrace()
                mutableUiState.postValue(UiState.Error)
            }

        }
    }

//    private suspend fun getPopularMovies() {
//    }

    fun searchMovie(query: String?) {

    }
}