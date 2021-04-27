package com.johnnyzhou.movieme.ui.person.detail;

import com.johnnyzhou.movieme.ui.person.Person;
import com.johnnyzhou.movieme.util.DateUtil;

public class PersonDetail extends Person {
    private static final String PROFILE_BASE_PATH = "http://image.tmdb.org/t/p/h632";

    private String biography;
    private String backdropUrl;
    private String birthday;

    public PersonDetail() {
    }

    public String getBiography() {
        return biography;
    }

    public String getBackdropUrl() {
        return PROFILE_BASE_PATH + getImagePath();
    }

    public String getBirthdayString() {
        if (birthday == null || birthday.isEmpty())
            return null;

        return "Date of Birth: " + DateUtil.convertDateFormat(birthday);
    }
}
