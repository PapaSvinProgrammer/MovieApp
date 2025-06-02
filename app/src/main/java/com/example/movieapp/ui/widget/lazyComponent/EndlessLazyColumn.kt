package com.example.movieapp.ui.widget.lazyComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T: Any> EndlessLazyColumn(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    items: List<T>,
    loadMore: () -> Unit,
    itemContent: @Composable (T) -> Unit
) {
    val reachBottom: Boolean by remember {
        derivedStateOf { listState.reachBottom() }
    }

    LaunchedEffect(reachBottom) {
        if (reachBottom) {
            loadMore()
        }
    }

    LazyColumn(
        modifier = modifier,
        state = listState,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement
    ) {
        items(items) {
            itemContent(it)
        }
    }
}

fun LazyListState.reachBottom(buffer: Int = 1): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()

    val condition1 = lastVisibleItem?.index != 0
    val condition2 = lastVisibleItem?.index == this.layoutInfo.totalItemsCount - buffer

    return condition1 && condition2
}