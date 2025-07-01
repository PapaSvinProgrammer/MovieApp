package com.example.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object FormatDate {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentYear(): Int {
        return LocalDate.now().year
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(date: String): String {
        val localDate = getLocalDate(date.split("T")[0])
        val pattern = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        return localDate.format(pattern)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getLocalDate(date: String): LocalDate {
        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(date, pattern)
    }
}