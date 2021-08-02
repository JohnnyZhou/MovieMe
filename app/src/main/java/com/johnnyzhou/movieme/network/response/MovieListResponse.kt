package com.johnnyzhou.movieme.network.response

import com.johnnyzhou.movieme.ui.movie.Movie

data class MovieListResponse(val movies: List<Movie>? = null)
