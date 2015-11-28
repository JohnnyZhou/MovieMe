package com.johnnyzhou.movieme.ui.person.list;

public class PersonListClickEvent {
    private final int position;

    public PersonListClickEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
