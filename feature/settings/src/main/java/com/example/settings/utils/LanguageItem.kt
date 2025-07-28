package com.example.settings.utils

internal sealed class LanguageItem(
    val text: String = "",
    val countryFlag: String = "",
    val slug: String = ""
) {
    data object Russia : LanguageItem(
        text = "Русский",
        countryFlag = "\uD83C\uDDF7\uD83C\uDDFA",
        slug = "ru"
    )

    data object UK : LanguageItem(
        text = "English",
        countryFlag = "\uD83C\uDDEC\uD83C\uDDE7",
        slug = "en"
    )
}

internal val languagesList = listOf(
    LanguageItem.Russia,
    LanguageItem.UK
)