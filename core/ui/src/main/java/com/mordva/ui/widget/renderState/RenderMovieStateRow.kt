package com.mordva.ui.widget.renderState

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mordva.model.movie.Movie
import com.mordva.ui.uiState.MovieUIState
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.LastItemCard
import com.mordva.ui.widget.listItems.MovieCard
import com.mordva.ui.widget.shimmer.ShimmerMovieRow

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
                name = it.name ?: "",
                image = it.poster?.url ?: "",
                rating = it.rating?.kp,
                top250 = it.top250,
                onClick = { onCLick(it) }
            )
        }
    )
}