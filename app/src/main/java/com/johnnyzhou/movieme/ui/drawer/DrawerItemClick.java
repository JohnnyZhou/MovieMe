package com.johnnyzhou.movieme.ui.drawer;

public class DrawerItemClick {
    public static final int MOVIE_POSITION = 0;
    public static final int PEOPLE_POSITION = 1;
    private int position;

    public DrawerItemClick(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
