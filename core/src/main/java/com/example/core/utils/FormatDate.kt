package com.example.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

object FormatDate {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentYear(): Int {
        return LocalDate.now().year
    }

    fun formatDate(date: String): String {
        return ""
    }
}