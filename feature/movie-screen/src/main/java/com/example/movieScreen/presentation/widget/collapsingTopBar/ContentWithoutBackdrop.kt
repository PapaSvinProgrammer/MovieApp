package com.example.movieScreen.presentation.widget.collapsingTopBar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.model.movie.Movie
import com.example.movieapp.ui.R

@Composable
internal fun ContentWithoutBackdrop(
    movie: Movie,
    date: String,
    genres: String,
    countries: String,
    length: String,
    age: String
) {
    Spacer(modifier = Modifier.height(100.dp))

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(movie.poster?.url)
            .crossfade(true)
            .build(),
        error = painterResource(R.drawable.ic_movie),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.height(300.dp).width(200.dp).clip(RoundedCornerShape(10.dp))
    )

    Spacer(modifier = Modifier.height(20.dp))

    ExpandedBasicContent(
        titleEn = movie.alternativeName ?: "",
        rating = movie.rating?.kp ?: 0f,
        votes = movie.votes?.kp ?: 0,
        top250 = movie.top250,
        date = date,
        genres = genres,
        countries = countries,
        length = length,
        age = age,
        title = {
            MovieLogo(
                url = null,
                name = movie.name ?: ""
            )
        }
    )
}