package com.example.movieapp.ui.widget.renderState

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.ui.uiState.FactUIState
import com.example.movieapp.ui.widget.lazyComponent.DefaultLazyRow
import com.example.movieapp.ui.widget.listItems.FactCard
import com.example.movieapp.ui.widget.shimmer.ShimmerFactRow
import com.example.network.module.movie.Fact

@Composable
fun RenderFactStateRow(
    state: FactUIState,
    title: String,
    onClick: (Fact) -> Unit
) {
    when (state) {
        FactUIState.Loading -> ShimmerFactRow()
        is FactUIState.Success -> {
            MainFactRowContent(
                list = state.data,
                title = title,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun MainFactRowContent(
    list: List<Fact>,
    title: String,
    onClick: (Fact) -> Unit
) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(15.dp)
    )

    DefaultLazyRow(
        list = list,
        key = { it.value },
        lastItemCard = {}
    ) {
        FactCard(
            text = it.value,
            isSpoiler = it.spoiler ?: false,
            onClick = { onClick(it) }
        )
    }
}