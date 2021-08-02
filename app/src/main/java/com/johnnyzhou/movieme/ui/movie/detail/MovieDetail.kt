package com.johnnyzhou.movieme.ui.movie.detail

import android.graphics.Bitmap
import com.johnnyzhou.movieme.ui.movie.Movie
import kotlinx.serialization.SerialName
import java.text.NumberFormat

class MovieDetail : Movie() {
    @SerialName("spoken_languages")
    private val languages: List<Language>? = null
    private val genres: List<Genre>? = null
    val tagline: String? = null
    val runtime: String? = null
        get() = "$field minutes"
    private val budget: String? = null
    private val bitmap: Bitmap? = null

    private class Language {
        val name: String? = null
    }

    private class Genre {
        val name: String? = null
    }

    fun getBudget(): String {
        val currencyFormat = NumberFormat.getCurrencyInstance()
        var currencyString = currencyFormat.format(budget!!.toInt().toLong())
        val centsIndex = currencyString.lastIndexOf(".00")
        if (centsIndex != -1) currencyString = currencyString.substring(0, centsIndex)
        return currencyString
    }

    fun getLanguages(): String {
        if (languages.isNullOrEmpty()) return ""

        val sb = StringBuilder()
        for (i in 0 until languages.size - 2) {
            sb.append(languages[i].name).append(", ")
        }

        sb.append(languages[languages.size - 1].name)

        return sb.toString()
    }

    fun getGenres(): String {
        if (genres.isNullOrEmpty()) return ""

        val sb = StringBuilder()

        for (i in 0 until genres.size - 2) {
            sb.append(genres[i].name).append(", ")
        }

        sb.append(genres[genres.size - 1].name)

        return sb.toString()
    }
}