package com.mordva.movieScreen.presentation.movie.widget.collapsingTopBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.model.movie.Movie
import com.mordva.util.convert.ConvertData
import com.mordva.util.convert.PrettyData

@Composable
internal fun ExpandedContent(
    movie: Movie,
    customRating: Int? = null,
    onEvaluate: () -> Unit,
    onAddIntoFuturePackage: () -> Unit,
    onShare: () -> Unit,
    onMore: () -> Unit
) {
    val date = ConvertData.convertDateCreated(movie.year, movie.releaseYears)
    val genres = movie.genres.take(2).joinToString(", ") { it.name }
    val countries = movie.countries.take(2).joinToString(", ") { it.name }
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
                age = age,
                customRating = customRating,
            )
        }
        else {
            ContentWithoutBackdrop(
                movie = movie,
                genres = genres,
                date = date,
                countries = countries,
                customRating = customRating,
                length = length,
                age = age
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        ExpandedNavigationBar(
            customRating = customRating,
            onEvaluate = onEvaluate,
            onShare = onShare,
            onMore = onMore,
            onAddIntoFuturePackage = onAddIntoFuturePackage
        )
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider()
    }
}