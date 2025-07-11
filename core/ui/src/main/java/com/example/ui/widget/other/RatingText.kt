package com.example.ui.widget.other

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.ui.theme.Gold
import com.example.ui.theme.Green
import com.example.utils.ConvertData

@Composable
fun RatingText(
    rating: Float,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    isTop: Int? = null
) {
    if (rating.toInt() == 0) return

    var color: Color

    if (rating.toInt() >= 7) {
        color = Green
    }
    else if (rating.toInt() >= 5) {
        color = Color.Gray
    }
    else {
        color = Color.Red
    }

    isTop?.let {
        color = Gold
    }

    Text(
        modifier = modifier,
        text = ConvertData.convertRatingKP(rating),
        fontWeight = fontWeight,
        fontSize = fontSize,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = color,
        textAlign = TextAlign.End
    )
}