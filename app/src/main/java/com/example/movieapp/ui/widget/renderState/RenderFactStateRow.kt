package com.example.movieapp.ui.widget.renderState

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.screen.uiState.FactUIState
import com.example.movieapp.ui.widget.component.TitleRow
import com.example.movieapp.ui.widget.lazyComponent.DefaultLazyRow
import com.example.movieapp.ui.widget.listItems.FactCard
import com.example.movieapp.ui.widget.listItems.LastItemCard
import com.example.movieapp.ui.widget.shimmer.ShimmerFactRow
import com.example.network.module.movie.Fact

@Composable
fun RenderFactStateRow(
    state: FactUIState,
    title: String,
    onClick: (Fact) -> Unit,
    onShowAll: () -> Unit
) {
    when (state) {
        FactUIState.Loading -> ShimmerFactRow()
        is FactUIState.Success -> {
            MainFactRowContent(
                list = state.data,
                title = title,
                onClick = onClick,
                onShowAll = onShowAll
            )
        }
    }
}

@Composable
private fun MainFactRowContent(
    list: List<Fact>,
    title: String,
    onClick: (Fact) -> Unit,
    onShowAll: () -> Unit
) {
    TitleRow(
        title = title,
        onClick = onShowAll
    )

    DefaultLazyRow(
        list = list,
        key = { it.value },
        lastItemCard = {
            LastItemCard(
                width = 200.dp,
                height = 160.dp
            )
        }
    ) {
        FactCard(
            text = it.value,
            isSpoiler = it.spoiler ?: false,
            onClick = { onClick(it) }
        )
    }
}