package com.mordva.ui.widget.other

import androidx.compose.ui.graphics.Color
import com.mordva.ui.theme.Green

fun Int.toRatingColor(): Color {
    var color: Color

    if (this >= 7) {
        color = Green
    }
    else if (this >= 5) {
        color = Color.Gray
    }
    else {
        color = Color.Red
    }
    
    return color
}