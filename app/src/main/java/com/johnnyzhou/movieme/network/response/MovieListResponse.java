package com.johnnyzhou.movieme.network.response;

import com.johnnyzhou.movieme.ui.movie.Movie;

import java.util.List;

public class MovieListResponse {
    private List<Movie> results;

    public MovieListResponse() {}

    public List<Movie> getMovies() {
        return results;
    }
}
