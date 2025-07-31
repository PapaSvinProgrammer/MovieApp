package com.example.collectionlist.presentation.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.image.CollectionMovie
import com.example.ui.widget.lazyComponent.EndlessLazyColumn
import com.example.ui.widget.listItems.CollectionListItem

@Composable
internal fun SuccessCollectionContent(
    modifier: Modifier,
    collectionMovies: List<CollectionMovie>,
    onLoadMore: () -> Unit,
    onClick: (CollectionMovie) -> Unit
) {
    EndlessLazyColumn(
        modifier = modifier,
        items = collectionMovies,
        loadMore = onLoadMore
    ) { index, item ->
        CollectionListItem(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(item) },
            leadingIcon = {
                Text(
                    text = (index + 1).toString(),
                    fontSize = 14.sp
                )
            },
            size = 75.dp,
            collectionMovie = item
        )
    }
}