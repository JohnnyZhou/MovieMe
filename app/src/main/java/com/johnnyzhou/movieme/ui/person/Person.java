package com.johnnyzhou.movieme.ui.person;

import com.johnnyzhou.movieme.ui.movie.Movie;
import com.johnnyzhou.movieme.util.NetworkUtil;

import java.util.List;

public class Person {
    private String id;
    private String name;
//    @SerializedName("known_for")
    private List<Movie> knownFor;
//    @SerializedName("profile_path")
    private String imagePath;

    public String getName() {
        return name;
    }

    public String getKnownFor() {
        if (knownFor == null || knownFor.size() == 0)
            return null;

        StringBuilder sb = new StringBuilder();
        sb.append("Known for: ");
        for (int i = 0; i < knownFor.size() - 2; i++)
            sb.append(knownFor.get(i).getTitle()).append(", ");

        sb.append(knownFor.get(knownFor.size() - 1).getTitle());

        return sb.toString();
    }

    public String getImageUrl() {
        return NetworkUtil.PROFILE_BASE_PATH + imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getId() {
        return id;
    }
}