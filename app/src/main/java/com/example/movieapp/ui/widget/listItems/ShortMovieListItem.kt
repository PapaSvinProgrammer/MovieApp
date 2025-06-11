package com.example.movieapp.ui.widget.listItems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.ui.widget.other.RatingText
import com.example.network.module.movie.ShortMovie

@Composable
fun ShortMovieListItem(
    shortMovie: ShortMovie,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clickable(onClick = onClick)
            .padding(15.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            NameContent(
                name = shortMovie.name,
                alternativeName = shortMovie.alternativeName,
                description = shortMovie.description
            )

            RatingText(
                modifier = Modifier.align(Alignment.CenterEnd),
                rating = shortMovie.rating ?: 0f
            )
        }
    }
}

@Composable
private fun BoxScope.NameContent(
    name: String?,
    alternativeName: String?,
    description: String?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterStart)
            .padding(end = 40.dp)
    ) {
        name?.let {
            Text(
                text = it,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        alternativeName?.let {
            Text(
                text = it,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        description?.let {
            Text(
                text = it,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}