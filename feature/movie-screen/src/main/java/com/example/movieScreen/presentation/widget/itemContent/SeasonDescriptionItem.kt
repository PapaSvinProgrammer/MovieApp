package com.example.movieScreen.presentation.widget.itemContent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.example.model.movie.Movie
import com.example.movieScreen.presentation.widget.component.SeasonDescription

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