package com.example.utils.convert

import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

object FormatDate {
    fun getCurrentYear(): Int {
        return LocalDate.now().year
    }

    fun formatDate(date: String): String {
        return try {
            val datePart = date.split("T")[0]
            val date = parseDateWithZeroYearSupport(datePart)
            val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
            date.format(formatter)
        } catch (e: Exception) {
            ""
        }
    }

    private fun parseDateWithZeroYearSupport(dateStr: String): LocalDate {
        return try {
            LocalDate.parse(dateStr)
        } catch (e: java.time.format.DateTimeParseException) {
            if (dateStr.startsWith("0000")) {
                DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd")
                    .parseDefaulting(ChronoField.YEAR_OF_ERA, 1)
                    .toFormatter()
                    .parse(dateStr, LocalDate::from)
            } else {
                throw e
            }
        }
    }
}
