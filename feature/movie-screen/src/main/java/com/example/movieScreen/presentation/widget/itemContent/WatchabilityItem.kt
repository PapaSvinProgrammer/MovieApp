package com.example.movieScreen.presentation.widget.itemContent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.example.model.movie.Movie
import com.example.movieScreen.presentation.widget.component.WatchabilityDescription

internal fun LazyListScope.watchabilityItem(movie: Movie) {
    if (movie.watchability.items.isEmpty()) return

    item {
        WatchabilityDescription(
            modifier = Modifier.clickable { },
            count = movie.watchability.items.size
        )
    }
}