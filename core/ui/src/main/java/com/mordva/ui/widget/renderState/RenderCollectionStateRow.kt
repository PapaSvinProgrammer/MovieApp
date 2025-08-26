package com.mordva.ui.widget.renderState

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mordva.model.image.CollectionMovie
import com.mordva.ui.uiState.CollectionUIState
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.CollectionCard
import com.mordva.ui.widget.listItems.LastItemCard
import com.mordva.ui.widget.shimmer.ShimmerCollectionRow

@Composable
fun RenderCollectionStateRow(
    state: CollectionUIState,
    title: String,
    onClick: (CollectionMovie) -> Unit,
    onShowAll: () -> Unit
) {
    when (state) {
        is CollectionUIState.Success -> {
            MainCollectionRow(
                collectionMovies = state.data,
                title = title,
                onClick = onClick,
                onShowAll = onShowAll
            )
        }
        else -> ShimmerCollectionRow()
    }
}

@Composable
private fun MainCollectionRow(
    collectionMovies: List<CollectionMovie>,
    title: String,
    onClick: (CollectionMovie) -> Unit,
    onShowAll: () -> Unit
) {
    TitleRow(
        title = title,
        onClick = onShowAll
    )

    DefaultLazyRow(
        list = collectionMovies,
        lastItemCard = {
            LastItemCard(
                width = 140.dp,
                height = 140.dp,
                onClick = onShowAll
            )
        },
        content = {
            CollectionCard(
                image = it.cover?.url ?: "",
                title = it.name ?: "",
                onClick = { onClick(it) }
            )
        }
    )
}