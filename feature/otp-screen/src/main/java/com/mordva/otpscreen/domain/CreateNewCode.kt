package com.mordva.otpscreen.domain

import javax.inject.Inject

internal class CreateNewCode @Inject constructor() {
    fun execute(
        code: List<Int?>,
        number: Int?,
        index: Int
    ): List<Int?> {
        return code.mapIndexed { currentIndex, currentNumber ->
            if (currentIndex == index) {
                number
            } else {
                currentNumber
            }
        }
    }
}
