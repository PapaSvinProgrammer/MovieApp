package com.example.settings.utils

fun Int.toAppTheme(): AppTheme {
    return when (this) {
        1 -> AppTheme.SYSTEM
        2 -> AppTheme.DARK
        else -> AppTheme.LIGHT
    }
}