package com.mordva.movieScreen.presentation.movie.widget.itemContent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.mordva.model.movie.Movie
import com.mordva.movieScreen.presentation.movie.widget.component.SeasonDescription

internal fun LazyListScope.seasonDescriptionItem(movie: Movie) {
    item {
        if (movie.isSeries == false) return@item

        movie.seasonsInfo?.let { seasonsInfo ->
            SeasonDescription(
                modifier = Modifier.clickable { },
                countSeasons = seasonsInfo.filter { it.number != 0 }.size,
                countSeries = seasonsInfo
                    .filter { it.number != 0 }
                    .sumOf { it.episodesCount ?: 0 }
            )
        }
    }
}