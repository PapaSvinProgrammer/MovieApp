package com.mordva.movieScreen.presentation.movie.widget.collapsingTopBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.ui.R
import com.mordva.ui.widget.other.toRatingColor

@Preview
@Composable
internal fun CustomRatingChip(rating: Int = 10) {
    Row(
        modifier = Modifier.background(
            color = rating.toRatingColor(),
            shape = CircleShape
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp)
                .padding(start = 5.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_star_fill),
            contentDescription = null,
            tint = Color.White
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = rating.toString(),
            fontSize = 13.sp,
            lineHeight = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}