package com.example.core.utils

import com.example.network.module.movie.Movie
import com.example.network.utils.Constants
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToInt

object ConvertData {
    fun convertRatingRange(position: ClosedFloatingPointRange<Float>): String {
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
    ): List<Pair<String, String>> {
        val res = mutableListOf<Pair<String, String>>()

        when (category) {
            "Фильмы" -> res.add(Constants.IS_SERIES_FIELD to Constants.FALSE)
            "Сериалы" -> res.add(Constants.IS_SERIES_FIELD to Constants.TRUE)
        }

        when (sortBy) {
            "Рейтингу" -> res.add(Constants.SORT_FIELD to Constants.RATING_KP_FIELD)
            "Популярности" -> res.add(Constants.SORT_FIELD to Constants.VOTES_KP_FIELD)
            "Дате" -> res.add(Constants.SORT_FIELD to Constants.YEAR_FIELD)
        }

        res.add(Constants.SORT_TYPE to Constants.SORT_DESC)

        genres.forEach { res.add(Constants.GENRES_NAME_FIELD to it) }
        counties.forEach { res.add(Constants.COUNTRIES_NAME_FIELD to it) }

        val startRating = rating.start.roundToInt()
        val endRating = rating.endInclusive.roundToInt()

        res.add(Constants.RATING_KP_FIELD to "$startRating-$endRating")
        //res[Constants.YEAR_FIELD] = year

        return res
    }

    fun getAlternativeNameForMovie(movie: Movie): String {
        movie.alternativeName?.let {
            return it
        }

        if (movie.releaseYears.isNotEmpty()) {
            val start = movie.releaseYears[0].start.toString()
            var end = movie.releaseYears[0].end.toString()

            if (movie.releaseYears[0].end == null) {
                end = "...."
            }

            return "$start-$end"
        }

        movie.year?.let {
            return it.toString()
        }

        if (movie.genres.isNotEmpty()) {
            return movie.genres.map { it.name }.joinToString(", ")
        }

        return ""
    }

    fun convertRatingKP(rating: Float): String {
        val temp = BigDecimal(rating.toDouble()).setScale(1, RoundingMode.HALF_EVEN)
        return temp.toString()
    }
}