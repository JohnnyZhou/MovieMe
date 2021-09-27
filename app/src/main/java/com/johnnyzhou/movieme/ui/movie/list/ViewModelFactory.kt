package com.johnnyzhou.movieme.ui.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.johnnyzhou.movieme.business.GetMovieUseCase
import dagger.Lazy

class ViewModelFactory(private val useCase: GetMovieUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        modelClass
            .getConstructor(GetMovieUseCase::class.java)
            .newInstance(useCase) as T
}