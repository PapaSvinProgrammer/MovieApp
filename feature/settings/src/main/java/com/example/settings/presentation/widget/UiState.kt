package com.example.settings.presentation.widget

internal data class UiState(
    val pinCodeSwitch: Boolean = false,
    val vibrationSwitch: Boolean = false,
    val alternativeSwitch: Boolean = false,
    val themeState: Int = 1,
    val currentIcon: Int = 1
)