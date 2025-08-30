package com.mordva.ui.widget.other

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.mordva.ui.theme.Gold
import com.mordva.ui.theme.Green
import com.mordva.ui.theme.Typography
import com.mordva.util.convert.ConvertData

@Composable
fun RatingText(
    rating: Float,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = Typography.bodyMedium.fontSize,
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