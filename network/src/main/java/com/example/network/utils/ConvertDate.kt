package com.example.network.utils

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

object ConvertDate {
    fun getCurrentDay(): String {
        val milli = System.currentTimeMillis()
        val date = Date(milli)
        val format = SimpleDateFormat("dd.MM", Locale.getDefault())

        return format.format(date)
    }

    fun getCurrentDate(): String {
        val milli = System.currentTimeMillis()
        val date = Date(milli)
        val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        return format.format(date)
    }
}