package com.example.movieScreen.widget.collapsingTopBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.model.movie.Movie
import com.example.ui.widget.component.isCorrectUrl
import com.example.utils.ConvertData
import com.example.utils.PrettyData

@Composable
fun ExpandedContent(movie: Movie) {
    val date = ConvertData.convertDateCreated(movie.year, movie.releaseYears)
    val genres = movie.genres.take(2).map { it.name }.joinToString(", ")
    val countries = movie.countries.take(2).map { it.name }.joinToString(", ")
    var length = PrettyData.getPrettyLength(movie.movieLength ?: 0)
    val age = PrettyData.getPrettyAgeRating(movie.ageRating ?: 0)

    if (movie.isSeries == true) {
        val seasonCount = movie.seasonsInfo?.count { it.number != 0 } ?: 0
        length = PrettyData.getPrettyCountSeasons(seasonCount)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (movie.backdrop?.url.isCorrectUrl() && movie.logo?.url.isCorrectUrl()) {
            ContentWithBackdrop(
                movie = movie,
                genres = genres,
                date = date,
                countries = countries,
                length = length,
                age = age
            )
        }
        else {
            ContentWithoutBackdrop(
                movie = movie,
                genres = genres,
                date = date,
                countries = countries,
                length = length,
                age = age
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        ExpandedNavigationBar()
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider()
    }
}