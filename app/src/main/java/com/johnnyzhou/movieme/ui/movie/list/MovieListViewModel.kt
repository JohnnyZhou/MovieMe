package com.johnnyzhou.movieme.ui.movie.list

import androidx.lifecycle.*
import com.johnnyzhou.movieme.business.GetMovieUseCase
import com.johnnyzhou.movieme.ui.common.UiState
import com.johnnyzhou.movieme.ui.movie.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val getMovieUseCase: GetMovieUseCase) : ViewModel() {
    private val mutableUiState = MutableLiveData<UiState>()
    private val mutableMovieLiveData = MutableLiveData<List<Movie>>()
    val uiState: LiveData<UiState> = mutableUiState
    val movieList: LiveData<List<Movie>> = mutableMovieLiveData

    init {
        getMovieList() // this doesn't work
        // https://stackoverflow.com/questions/61972739/use-the-same-instance-of-view-model-in-multiple-fragments-using-dagger2
    }

    private val testLive: LiveData<Boolean> = liveData {
        emit(true)
    }

    private fun getMovieList() {
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

    override fun onCleared() {
        super.onCleared()
    }

    fun searchMovie(query: String?) {

    }
}