package com.johnnyzhou.movieme.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val mutableUiState = MutableLiveData(false)
    val uiState: LiveData<Boolean> = mutableUiState

    // check screen re-configuration

    fun onFetchData() {
        // getMovieList from useCase then repository
        // launch coroutine that's lifecycle/viewModel aware
        viewModelScope.launch {

        }
    }

    fun getMovieList() {

    }

    fun searchMovie(query: String?) {

    }

    fun getPopularMovies() {

    }
}