package com.example.movieapp.ui.widget.collapsingTopBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.core.utils.ConvertData
import com.example.movieapp.R
import com.example.network.module.movie.Movie

@Composable
fun ExpandedMovieContent(movie: Movie) {
    val date = ConvertData.convertDateCreated(movie.year, movie.releaseYears)
    val genres = movie.genres.take(2).map { it.name }.joinToString(", ")
    val countries = movie.countries.take(2).map { it.name }.joinToString(", ")
    val length = ConvertData.getPrettyLength(movie.movieLength ?: 0)
    val age = ConvertData.getPrettyAgeRating(movie.ageRating ?: 0)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(360.dp))

        ExpandedBasicContent(
            rating = movie.rating?.kp ?: 0f,
            votes = movie.votes?.kp ?: 0,
            top250 = movie.top250,
            date = date,
            genres = genres,
            countries = countries,
            length = length,
            age = age,
            title = { MovieLogo(movie) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        ExpandedNavigationBar()
    }
}

@Composable
private fun MovieLogo(movie: Movie) {
    if (movie.logo?.url != null) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.logo?.url)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.ic_movie),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 45.dp)
        )
    }
    else {
        Text(
            text = movie.name ?: "",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}