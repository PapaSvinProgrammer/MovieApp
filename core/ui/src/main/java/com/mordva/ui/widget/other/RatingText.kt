package com.mordva.ui.widget.other

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.R
import com.mordva.ui.theme.Gold
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

    var color = rating.toInt().toRatingColor()
    val horizontalPadding = if (isTop != null) 15.dp else 6.dp

    isTop?.let {
        color = Gold
    }

    Box(modifier = modifier) {
        isTop?.let {
            Icon(
                painter = painterResource(R.drawable.ic_branch),
                contentDescription = null,
                tint = Gold,
                modifier = Modifier
                    .size(15.dp)
                    .align(Alignment.CenterStart)
                    .padding(start = 5.dp)
                    .scale(scaleX = -1f, scaleY = 1f)
            )
        }

        Text(
            text = ConvertData.convertRatingKP(rating),
            fontWeight = fontWeight,
            fontSize = fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = color,
            textAlign = TextAlign.End,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = horizontalPadding)
        )

        isTop?.let {
            Icon(
                painter = painterResource(R.drawable.ic_branch),
                contentDescription = null,
                tint = Gold,
                modifier = Modifier
                    .size(15.dp)
                    .align(Alignment.CenterEnd)
                    .padding(end = 5.dp)
            )
        }
    }
}