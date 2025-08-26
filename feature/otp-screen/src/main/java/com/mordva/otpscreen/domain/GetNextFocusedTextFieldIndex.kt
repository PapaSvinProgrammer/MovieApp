package com.mordva.otpscreen.domain

import javax.inject.Inject

internal class GetNextFocusedTextFieldIndex @Inject constructor() {
    fun execute(
        currentCode: List<Int?>,
        currentFocusedIndex: Int?
    ): Int? {
        if (currentFocusedIndex == null) {
            return null
        }

        if (currentFocusedIndex == 3) {
            return currentFocusedIndex
        }

        return GetFirstEmptyFieldIndexAfterFocusedIndex().execute(
            code = currentCode,
            currentFocusedIndex = currentFocusedIndex
        )
    }
}