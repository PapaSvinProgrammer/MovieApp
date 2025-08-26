package com.mordva.collectionlist.presentation.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mordva.model.image.CollectionMovie
import com.mordva.ui.uiState.CollectionUIState
import com.mordva.ui.widget.shimmer.ShimmerMovieDetailList

@Composable
internal fun RenderCollectionState(
    modifier: Modifier = Modifier,
    state: CollectionUIState,
    onClick: (CollectionMovie) -> Unit,
    onLoadMore: () -> Unit
) {
    when (state) {
        CollectionUIState.Loading -> ShimmerMovieDetailList(modifier)
        is CollectionUIState.Success -> {
            SuccessCollectionContent(
                modifier = modifier,
                collectionMovies = state.data,
                onClick = onClick,
                onLoadMore = onLoadMore
            )
        }
    }
}