package com.mordva.ui.widget.lazyComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> EndlessLazyVerticalStaggeredGrid(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 15.dp),
    verticalItemSpacing: Dp = 10.dp,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(10.dp),
    columns: StaggeredGridCells = StaggeredGridCells.Fixed(2),
    gridState: LazyStaggeredGridState = rememberLazyStaggeredGridState(),
    list: List<T>,
    key: ((item: T) -> Any)? = null,
    span: ((item: T) -> StaggeredGridItemSpan)? = null,
    onLoadMore: () -> Unit,
    content: @Composable (T) -> Unit
) {
    val reachBottom: Boolean by remember {
        derivedStateOf { gridState.reachBottom() }
    }

    LaunchedEffect(reachBottom) {
        if (reachBottom) { onLoadMore() }
    }

    LazyVerticalStaggeredGrid(
        modifier = modifier,
        state = gridState,
        columns = columns,
        contentPadding = contentPadding,
        verticalItemSpacing = verticalItemSpacing,
        horizontalArrangement = horizontalArrangement,
    ) {
        items(
            items = list,
            key = key,
            span = span
        ) { item ->
            content(item)
        }
    }
}

fun LazyStaggeredGridState.reachBottom(buffer: Int = 1): Boolean {
    val layoutInfo = this.layoutInfo
    val totalItemsCount = layoutInfo.totalItemsCount
    val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull() ?: return false

    if (totalItemsCount == 0) return false

    val isLastVisible = lastVisibleItem.index >= totalItemsCount - buffer

    return isLastVisible
}