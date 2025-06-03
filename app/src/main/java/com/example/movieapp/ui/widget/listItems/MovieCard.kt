package com.example.movieapp.ui.widget.listItems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.movieapp.R
import com.example.movieapp.ui.widget.chips.RatingChip
import com.example.network.module.movie.Movie

@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(160.dp)
            .height(260.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.poster?.url)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_movie),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)).weight(5f)
            )

            Text(
                text = movie.name ?: "",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 10.dp).weight(1f)
            )
        }

        RatingChip(
            modifier = Modifier.align(Alignment.TopStart),
            rating = movie.rating?.kp ?: 0f,
            top = movie.top250
        )
    }
}