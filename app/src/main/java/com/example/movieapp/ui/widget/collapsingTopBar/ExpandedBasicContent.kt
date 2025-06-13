package com.example.movieapp.ui.widget.collapsingTopBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.utils.ConvertData
import com.example.movieapp.ui.widget.other.RatingText

@Composable
internal fun ExpandedBasicContent(
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

        Row {
            RatingText(
                rating = rating,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                isTop = top250
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = ConvertData.getPrettyVotes(votes),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = "$date, $genres",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 30.dp)
        )

        Text(
            text = "$countries, $length, $age",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
    }
}