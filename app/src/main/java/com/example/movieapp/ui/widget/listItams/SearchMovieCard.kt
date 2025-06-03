package com.example.movieapp.ui.widget.listItams

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.movieapp.R
import com.example.movieapp.app.utils.ConvertData
import com.example.movieapp.ui.widget.other.RatingText
import com.example.network.module.movie.Movie

@Composable
fun SearchMovieCard(
    movie: Movie,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clickable(onClick = onClick)
            .padding(15.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.poster?.url)
                .crossfade(true)
                .build(),
            contentDescription = null,
            error = painterResource(R.drawable.ic_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(100.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.width(15.dp))
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DetailInfoContent(movie)
                RatingText(
                    modifier = Modifier.weight(1f),
                    rating = movie.rating?.kp ?: 0f
                )
            }
        }
    }
}

@Composable
private fun RowScope.DetailInfoContent(movie: Movie) {
    Column(
        modifier = Modifier.fillMaxSize().weight(6f),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = movie.name ?: "",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        AlternativeName(movie)
        DateRange(movie)
    }
}

@Composable
private fun AlternativeName(movie: Movie) {
    var name = ConvertData.getAlternativeNameForMovie(movie)

    if (name.isEmpty()) {
        name = movie.genres.map { it.name }.joinToString(", ")
    }

    Text(
        text = name,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun DateRange(movie: Movie) {
    val text: String

    if (movie.releaseYears.isNotEmpty()) {
        val start = movie.releaseYears[0].start
        val end = movie.releaseYears[0].end
        text = "$start-$end"
    }
    else {
        text = movie.year.toString()
    }

    Text(
        text = text,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}