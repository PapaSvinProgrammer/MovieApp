package com.example.settings.presentation.widget.state

import com.example.settings.utils.LanguageItem

internal data class LanguageUiState(
    val query: String = "",
    val languages: List<LanguageItem> = listOf()
)