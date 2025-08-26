package com.mordva.otpscreen.presentation.widget.state

internal data class UiState(
    val code: List<Int?> = (1..4).map { null },
    val focusedIndex: Int? = null,
    val isValid: Boolean? = null,
    val defaultCode: String = ""
)