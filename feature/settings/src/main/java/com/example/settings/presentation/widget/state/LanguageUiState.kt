package com.example.settings.presentation.widget.state

import com.example.settings.utils.LanguageItem

internal data class LanguageUiState(
    val query: String = "",
    val defaultLanguageSlug: String = "ru",
    val languages: List<LanguageItem> = listOf()
)