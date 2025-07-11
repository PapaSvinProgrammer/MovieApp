package com.example.ui.widget.renderState

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.model.movie.Movie
import com.example.ui.uiState.MovieUIState
import com.example.ui.widget.component.TitleRow
import com.example.ui.widget.lazyComponent.DefaultLazyRow
import com.example.ui.widget.listItems.LastItemCard
import com.example.ui.widget.listItems.MovieCard
import com.example.ui.widget.shimmer.ShimmerMovieRow

@Composable
fun RenderMovieStateRow(
    state: MovieUIState,
    title: String,
    onClick: (Movie) -> Unit,
    onShowAll: () -> Unit
) {
    when (state) {
        is MovieUIState.Success -> {
            MainMovieRow(
                title = title,
                movies = state.data,
                onCLick = onClick,
                onShowAll = onShowAll
            )
        }
        else -> ShimmerMovieRow()
    }
}

@Composable
private fun MainMovieRow(
    title: String,
    movies: List<Movie>,
    onCLick: (Movie) -> Unit,
    onShowAll: () -> Unit
) {
    TitleRow(
        title = title,
        onClick = onShowAll
    )

    DefaultLazyRow(
        list = movies,
        key = { it.id },
        lastItemCard = {
            LastItemCard(
                width = 160.dp,
                height = 260.dp,
                onClick = onShowAll
            )
        },
        content = {
            MovieCard(
                movie = it,
                onClick = { onCLick(it) }
            )
        }
    )
}