package com.mordva.movielist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.model.movie.Movie
import com.mordva.ui.uiState.MovieUIState
import com.mordva.ui.widget.lazyComponent.EndlessLazyColumn
import com.mordva.ui.widget.listItems.MovieListCard
import com.mordva.ui.widget.shimmer.ShimmerMovieDetailList

@Composable
internal fun RenderResult(
    state: MovieUIState,
    modifier: Modifier,
    onClick: (Movie) -> Unit,
    onSettingsClick: (Movie) -> Unit,
    onLoadMore: () -> Unit
) {
    when (state) {
        MovieUIState.Loading -> ShimmerMovieDetailList(modifier)
        is MovieUIState.Success -> {
            MainPersonContent(
                modifier = modifier,
                list = state.data,
                onClick = onClick,
                onLoadMore = onLoadMore,
                onSettingsClick = onSettingsClick
            )
        }
    }
}

@Composable
private fun MainPersonContent(
    modifier: Modifier = Modifier,
    list: List<Movie>,
    onClick: (Movie) -> Unit,
    onLoadMore: () -> Unit,
    onSettingsClick: (Movie) -> Unit
) {
    EndlessLazyColumn(
        modifier = modifier,
        items = list,
        loadMore = onLoadMore
    ) { _, item ->
        MovieListCard(
            movie = item,
            onClick = { onClick(item) },
            onSettingsClick = { onSettingsClick(item) }
        )

        HorizontalDivider(modifier = Modifier.padding(start = 15.dp))
    }
}
