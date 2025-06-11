package com.example.movieapp.ui.widget.other

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.core.utils.ConvertData
import com.example.movieapp.ui.theme.Green

@Composable
fun RatingText(
    rating: Float,
    modifier: Modifier = Modifier
) {
    if (rating.toInt() == 0) return

    val color: Color

    if (rating.toInt() >= 7) {
        color = Green
    }
    else if (rating.toInt() >= 5) {
        color = Color.Gray
    }
    else {
        color = Color.Red
    }

    Text(
        modifier = modifier,
        text = ConvertData.convertRatingKP(rating),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = color,
        textAlign = TextAlign.End
    )
}