package com.example.movieapp.app.utils

import com.example.network.module.movie.Movie
import com.example.network.utils.Constants
import kotlin.math.roundToInt

object ConvertData {
    fun convertRating(position: ClosedFloatingPointRange<Float>): String {
        val start = position.start.roundToInt()
        val end = position.endInclusive.roundToInt()

        val startStr = "от $start"
        val endStr = "до $end"

        if (start == end) {
            return "только $start"
        }

        if (start == 0 && end == 10) {
            return "неважно"
        }

        if (start == 0) {
            return endStr
        }

        if (end == 10) {
            return startStr
        }

        return startStr + endStr
    }

    fun convertQueryParameters(
        category: String,
        sortBy: String,
        genres: List<String>,
        counties: List<String>,
        year: String,
        rating: ClosedFloatingPointRange<Float>
    ): Map<String, String> {
        val res = mutableMapOf<String, String>()

        when (category) {
            "Фильмы" -> res[Constants.IS_SERIES_FIELD] = Constants.FALSE
            "Сериалы" -> res[Constants.IS_SERIES_FIELD] = Constants.TRUE
        }

        when (sortBy) {
            "Рейтингу" -> res[Constants.SORT_FIELD] = Constants.RATING_KP_FIELD
            "Популярности" -> res[Constants.SORT_FIELD] = Constants.VOTES_KP_FIELD
            "Дате" -> res[Constants.SORT_FIELD] = Constants.YEAR_FIELD
        }

        res[Constants.SORT_TYPE] = Constants.SORT_DESC

        if (genres.isNotEmpty()) {
            res[Constants.GENRES_NAME_FIELD] = genres.joinToString(",")
        }

        if (counties.isNotEmpty()) {
            res[Constants.COUNTRIES_NAME_FIELD] = counties.joinToString(",")
        }

        val startRating = rating.start.roundToInt()
        val endRating = rating.endInclusive.roundToInt()

        res[Constants.RATING_KP_FIELD] = "$startRating-$endRating"
        //res[Constants.YEAR_FIELD] = year

        return res
    }

    fun getAlternativeNameForMovie(movie: Movie): String {
        movie.alternativeName?.let {
            return it
        }

        if (movie.releaseYears.isNotEmpty() ) {
            val start = movie.releaseYears[0].start
            val end = movie.releaseYears[0].end
            return "$start-$end"
        }

        movie.year?.let {
            return it.toString()
        }

        return ""
    }
}