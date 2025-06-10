package com.example.movieapp.ui.widget.lazyComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> EndlessLazyVerticalGrid(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 15.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(15.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(10.dp),
    columns: GridCells = GridCells.Fixed(2),
    gridState: LazyGridState = rememberLazyGridState(),
    list: List<T>,
    key: ((item: T) -> Any)? = null,
    onLoadMore: () -> Unit,
    content: @Composable (T) -> Unit
) {
    val reachBottom: Boolean by remember {
        derivedStateOf { gridState.reachBottom() }
    }

    LaunchedEffect(reachBottom) {
        if (reachBottom) {
            onLoadMore()
        }
    }

    LazyVerticalGrid(
        modifier = modifier,
        columns = columns,
        state = gridState,
        contentPadding = contentPadding,
        horizontalArrangement = horizontalArrangement,
        verticalArrangement = verticalArrangement
    ) {
        items(
            items = list,
            key = key
        ) {
            content(it)
        }
    }
}

fun LazyGridState.reachBottom(buffer: Int = 1): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    val condition1 = lastVisibleItem?.index != 0
    val condition2 = lastVisibleItem?.index == this.layoutInfo.totalItemsCount - buffer

    return condition1 && condition2
}