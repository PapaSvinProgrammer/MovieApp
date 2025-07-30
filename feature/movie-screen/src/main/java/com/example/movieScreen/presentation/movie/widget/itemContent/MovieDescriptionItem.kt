package com.example.movieScreen.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.model.movie.Movie
import com.example.movieScreen.presentation.movie.widget.component.MovieDescription

internal fun LazyListScope.movieDescriptionItem(movie: Movie) {
    item {
        Spacer(modifier = Modifier.height(20.dp))

        MovieDescription(
            title = movie.shortDescription ?: "",
            description = movie.description ?: ""
        ) { }

        Spacer(modifier = Modifier.height(15.dp))
    }
}