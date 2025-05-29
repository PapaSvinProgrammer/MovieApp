package com.example.movieapp.ui.widget.componentRow

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.widget.listItams.LastItemCard
import com.example.movieapp.ui.widget.listItams.MovieCard
import com.example.network.module.movie.Movie

@Composable
fun MovieLazyRow(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onClick: (Movie) -> Unit,
    onShowAll: () -> Unit
) {
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(listState, SnapPosition.Start)

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 15.dp),
        state = listState,
        flingBehavior = flingBehavior
    ) {
        items(movies) {
            MovieCard(
                movie = it,
                onClick = { onClick(it) }
            )
        }

        item {
            LastItemCard(
                onClick = onShowAll,
                height = 260.dp,
                width = 160.dp
            )
        }
    }
}