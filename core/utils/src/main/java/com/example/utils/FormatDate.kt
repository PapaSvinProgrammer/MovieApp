package com.example.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object FormatDate {
    fun getCurrentYear(): Int {
        return LocalDate.now().year
    }

    fun formatDate(date: String): String {
        val localDate = getLocalDate(date.split("T")[0])
        val pattern = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        return localDate.format(pattern)
    }

    private fun getLocalDate(date: String): LocalDate {
        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(date, pattern)
    }
}