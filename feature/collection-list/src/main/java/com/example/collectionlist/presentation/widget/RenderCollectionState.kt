package com.example.collectionlist.presentation.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.model.image.CollectionMovie
import com.example.ui.uiState.CollectionUIState
import com.example.ui.widget.shimmer.ShimmerMovieDetailList

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