package com.example.ui.widget.lazyComponent

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

@Composable
fun <T> DefaultLazyRow(
    modifier: Modifier = Modifier,
    list: List<T>,
    key: ((item: T) -> Any)? = null,
    lastItemCard: @Composable () -> Unit,
    content: @Composable (T) -> Unit
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
        items(
            items = list,
            key = key
        ) {
            content(it)
        }

        item {
            lastItemCard()
        }
    }
}