package com.johnnyzhou.movieme.ui.movie.list;

public class MovieListClickEvent {
    private final int position;

    public MovieListClickEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
