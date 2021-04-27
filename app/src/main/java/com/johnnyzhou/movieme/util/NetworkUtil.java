package com.johnnyzhou.movieme.util;

public class NetworkUtil {
    public static final String API_KEY = "42af06c42da2647304c1037af14aea64";
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
