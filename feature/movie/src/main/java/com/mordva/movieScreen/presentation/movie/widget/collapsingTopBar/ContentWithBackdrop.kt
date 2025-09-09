package com.mordva.movieScreen.presentation.movie.widget.collapsingTopBar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.model.movie.Movie

@Composable
internal fun ContentWithBackdrop(
    movie: Movie,
    date: String,
    genres: String,
    countries: String,
    length: String,
    age: String,
    customRating: Int? = null,
) {
    Spacer(modifier = Modifier.height(360.dp))

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
        customRating = customRating,
        title = {
            MovieLogo(
                url = movie.logo?.url,
                name = movie.name ?: ""
            )
        }
    )
}