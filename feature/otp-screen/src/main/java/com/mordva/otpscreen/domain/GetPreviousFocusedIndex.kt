package com.mordva.otpscreen.domain

import javax.inject.Inject

internal class GetPreviousFocusedIndex @Inject constructor() {
    fun execute(currentIndex: Int?): Int? {
        return currentIndex?.minus(1)?.coerceAtLeast(0)
    }
}