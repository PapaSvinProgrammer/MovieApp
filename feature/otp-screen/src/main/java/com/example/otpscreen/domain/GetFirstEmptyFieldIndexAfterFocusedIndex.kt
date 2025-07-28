package com.example.otpscreen.domain

import javax.inject.Inject

internal class GetFirstEmptyFieldIndexAfterFocusedIndex @Inject constructor() {
    fun execute(
        code: List<Int?>,
        currentFocusedIndex: Int
    ): Int {
        code.forEachIndexed { index, number ->
            if (index <= currentFocusedIndex) {
                return@forEachIndexed
            }

            if (number == null) {
                return index
            }
        }

        return currentFocusedIndex
    }
}
