package com.johnnyzhou.movieme.network.response;

import com.johnnyzhou.movieme.ui.person.Person;

import java.util.List;

public class PersonListResponse {
    private List<Person> results;

    public PersonListResponse() {
    }

    public List<Person> getPeople() {
        return results;
    }
}
