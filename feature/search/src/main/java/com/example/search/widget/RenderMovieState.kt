package com.example.search.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.model.movie.Movie
import com.example.ui.uiState.MovieUIState
import com.example.ui.widget.lazyComponent.EndlessLazyColumn
import com.example.ui.widget.listItems.MovieDetailCard
import com.example.ui.widget.shimmer.ShimmerMovieDetailList

@Composable
internal fun RenderMovieState(
    modifier: Modifier,
    state: MovieUIState,
    onClick: (Movie) -> Unit,
    loadMore: () -> Unit
) {
    when (state) {
        is MovieUIState.Success -> {
            MainPersonContent(
                modifier = modifier,
                movies = state.data,
                onClick = onClick,
                loadMore = loadMore
            )
        }
        else -> ShimmerMovieDetailList(modifier)
    }
}

@Composable
private fun MainPersonContent(
    modifier: Modifier,
    movies: List<Movie>,
    onClick: (Movie) -> Unit,
    loadMore: () -> Unit
) {

    EndlessLazyColumn(
        modifier = modifier,
        items = movies,
        loadMore = loadMore
    ) { _, it ->
        MovieDetailCard(it) { onClick(it) }
        HorizontalDivider(modifier = Modifier.padding(start = 110.dp))
    }
}