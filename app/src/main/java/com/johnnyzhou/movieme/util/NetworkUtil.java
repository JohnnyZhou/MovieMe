package com.johnnyzhou.movieme.util;

public class NetworkUtil {
    public static final String API_KEY = "API_KEY_HERE";
    public static final String API_URL = "http://api.themoviedb.org/3/";
    public static final String API_HOST_NAME = "api.themoviedb.org";

    public static final String PROFILE_BASE_PATH = "http://image.tmdb.org/t/p/w185";
    public static final String BACKDROP_BASE_PATH = "http://image.tmdb.org/t/p/w780";
    public static final String POSTER_BASE_PATH = "http://image.tmdb.org/t/p/w185";

    public static final String NETWORK_ERROR_MSG = "Could not connect to the server.\nTry again later.";

    private NetworkUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instance should not be created");
    }
}
