package com.example.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.network.module.movie.Movie
import com.example.network.module.totalValue.ReleaseYears
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertYearRange(start: Int, end: Int): String {
        if (start == end) {
            return start.toString()
        }

        val currentYear = FormatDate.getCurrentYear()

        if (start == Constants.LAST_YEAR && end == currentYear) {
            return "Любой"
        }

        if (start == Constants.LAST_YEAR) {
            return "по $end"
        }

        if (end == currentYear) {
            return "с $start"
        }

        return "с $start по $end"
    }

    fun convertDateCreated(year: Int?, releaseYears: List<ReleaseYears>): String {
        year?.let {
            return it.toString()
        }

        if (releaseYears.isNotEmpty()) {
            val start = releaseYears[0].start
            val end = releaseYears[0].end

            start?.let {
                if (end == null) {
                    return "$start-..."
                }

                return "$start-$end"
            }
        }

       return ""
    }

    fun convertQueryParameters(
        category: String,
        sortBy: String,
        genres: List<String>,
        counties: List<String>,
        year: Pair<Int, Int>,
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
        res.add(Constants.YEAR_FIELD to "${year.first}-${year.second}")

        return res
    }

    fun getAlternativeNameForMovie(movie: Movie): String {
        movie.alternativeName?.let {
            return it
        }

        val year = convertDateCreated(movie.year, movie.releaseYears)

        if (year.isNotEmpty()) {
            return year
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

    fun getPrettyInt(number: Int): String {
        val str = number.toString()
        val res = StringBuilder()
        var count = 0

        for (i in str.lastIndex downTo 0) {
            if (count == 3) {
                res.append(" ")
                count = 0
            }

            count++
            res.append(str[i])
        }

        return res.reversed().toString()
    }

    fun getPrettyAge(value: Int): String {
        val res = when (value % 10) {
            1 -> "год"
            2, 3, 4 -> "года"
            else -> "лет"
        }

        return "$value $res"
    }

    fun getPrettyGrowth(value: Int): String {
        var res = ""

        res += value / 100
        res += "."
        res += value % 100

        return "$res м"
    }
}