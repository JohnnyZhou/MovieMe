package com.johnnyzhou.movieme.ui.movie

import com.johnnyzhou.movieme.util.DateUtils.Companion.convertDateFormat
import com.johnnyzhou.movieme.util.NetworkUtil
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class Movie {
    val id: String? = null
    val title: String? = null

    @SerialName("release_date")
    val releaseDate: String? = null
        get() = convertDateFormat(field)

    @SerialName("overview")
    val summary: String? = null

    @SerialName("poster_path")
    val posterUrl: String? = null
        get() = NetworkUtil.POSTER_BASE_PATH + field

    @SerialName("backdrop_path")
    val backdropUrl: String? = null
        get() = NetworkUtil.BACKDROP_BASE_PATH + field
}