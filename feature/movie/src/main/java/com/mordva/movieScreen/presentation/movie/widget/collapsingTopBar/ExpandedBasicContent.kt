package com.mordva.movieScreen.presentation.movie.widget.collapsingTopBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mordva.ui.widget.other.RatingText
import com.mordva.util.convert.PrettyData

@Composable
internal fun ExpandedBasicContent(
    titleEn: String,
    rating: Float,
    votes: Int,
    date: String,
    genres: String,
    countries: String,
    length: String,
    age: String,
    top250: Int? = null,
    title: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        title()

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 20.dp)
        ) {
            RatingText(
                rating = rating,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                isTop = top250
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = PrettyData.getPrettyVotes(votes),
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = titleEn,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }

        Text(
            text = "$date, $genres",
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 30.dp)
        )

        Text(
            text = "$countries, $length, $age",
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
    }
}