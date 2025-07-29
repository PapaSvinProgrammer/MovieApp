package com.example.movieScreen.presentation.widget.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.model.person.PersonMovie
import com.example.movieScreen.presentation.widget.listItem.PersonMovieListItem

@Composable
internal fun PersonGridHorizontalList(
    modifier: Modifier = Modifier,
    list: List<PersonMovie>,
    onClick: (PersonMovie) -> Unit
) {
    val lazyState = rememberLazyGridState()
    val flingBehavior = rememberSnapFlingBehavior(lazyState, SnapPosition.Start)

    LazyHorizontalGrid(
        modifier = modifier.height(300.dp),
        rows = GridCells.Fixed(3),
        state = lazyState,
        flingBehavior = flingBehavior
    ) {
        items(list) {
            PersonMovieListItem(
                modifier = Modifier.clickable { onClick(it) },
                person = it
            )
        }
    }
}