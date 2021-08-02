package com.johnnyzhou.movieme.ui.movie

import com.johnnyzhou.movieme.util.DateUtils.Companion.convertDateFormat
import com.johnnyzhou.movieme.util.NetworkUtil

open class Movie {
    val id: String? = null
    val title: String? = null

    //    @SerializedName("release_date")
    val releaseDate: String? = null
        get() = convertDateFormat(field)

    //    @SerializedName("overview")
    val summary: String? = null

    //    @SerializedName("poster_path")
    val posterUrl: String? = null
        get() = NetworkUtil.POSTER_BASE_PATH + field

    //    @SerializedName("backdrop_path")
    val backdropUrl: String? = null
        get() = NetworkUtil.BACKDROP_BASE_PATH + field
}