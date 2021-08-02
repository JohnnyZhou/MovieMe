package com.johnnyzhou.movieme.network.response

import com.johnnyzhou.movieme.ui.movie.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponse(@SerialName("results")
                             val movies: List<Movie>? = null)
