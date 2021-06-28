package com.johnnyzhou.movieme.ui.movie;

import com.google.gson.annotations.SerializedName;
import com.johnnyzhou.movieme.util.DateUtils;
import com.johnnyzhou.movieme.util.NetworkUtil;

public class Movie {
    private String id;
    private String title;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("overview")
    private String summary;
    @SerializedName("poster_path")
    private String posterUrl;
    @SerializedName("backdrop_path")
    private String backdropUrl;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getPosterUrl() {
        return NetworkUtil.POSTER_BASE_PATH + posterUrl;
    }

    public String getBackdropUrl() {
        return NetworkUtil.BACKDROP_BASE_PATH + backdropUrl;
    }

    public String getReleaseDate() {
        return DateUtils.Companion.convertDateFormat(releaseDate);
    }
}