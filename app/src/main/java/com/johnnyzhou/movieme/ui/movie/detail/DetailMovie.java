package com.johnnyzhou.movieme.ui.movie.detail;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;
import com.johnnyzhou.movieme.ui.movie.Movie;

import java.text.NumberFormat;
import java.util.List;

public class DetailMovie extends Movie {
    @SerializedName("spoken_languages")
    private List<Language> languages;
    private List<Genre> genres;
    private String tagline;
    private String runtime;
    private String budget;
    private Bitmap bitmap;

    private static class Language {
        private String name;

        public String getName() {
            return name;
        }
    }

    private static class Genre {
        private String name;

        public String getName() {
            return name;
        }
    }

    public DetailMovie() {
    }

    public String getTagline() {
        return tagline;
    }

    public String getRuntime() {
        return runtime + " minutes";
    }

    public String getBudget() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        String currencyString = currencyFormat.format(Integer.parseInt(budget));

        int centsIndex = currencyString.lastIndexOf(".00");
        if (centsIndex != -1)
            currencyString = currencyString.substring(0, centsIndex);

        return currencyString;
    }

    public String getLanguages() {
        if (languages.size() == 0) return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < languages.size() - 2; i++)
            sb.append(languages.get(i).getName()).append(", ");

        sb.append(languages.get(languages.size() - 1).getName());

        return sb.toString();
    }

    public String getGenres() {
        if (genres.size() == 0) return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < genres.size() - 2; i++)
            sb.append(genres.get(i).getName()).append(", ");

        sb.append(genres.get(genres.size() - 1).getName());

        return sb.toString();
    }
}