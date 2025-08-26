package com.mordva.util.convert

object PrettyData {
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
        val res = when {
            value % 100 in 11..14 -> "лет"
            value % 10 == 1 -> "год"
            value % 10 in 2..4 -> "года"
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

    fun getPrettyVotes(value: Int): String {
        return "${value / 1000}K"
    }

    fun getPrettyAgeRating(value: Int): String {
        return "$value+"
    }

    fun getPrettyLength(value: Int): String {
        val hours = value / 60
        val minutes = value % 60
        return "$hours ч $minutes мин"
    }

    fun getPrettyCountSeasons(value: Int): String {
        return when {
            value % 100 in 11..14 -> "$value сезонов"
            value % 10 == 1 -> "$value сезон"
            value % 10 in 2..4 -> "$value сезона"
            else -> "$value сезонов"
        }
    }

    fun getPrettyCountSeries(value: Int): String {
        return when {
            value % 100 in 11..14 -> "$value серий"
            value % 10 == 1 -> "$value серия"
            value % 10 in 2..4 -> "$value серии"
            else -> "$value серий"
        }
    }

    fun getPrettyString(value: String): String {
        return value.lowercase().replaceFirstChar { it.uppercase() }
    }
}