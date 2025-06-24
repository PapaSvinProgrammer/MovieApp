package com.example.movieapp.ui.widget.renderState

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.movieapp.R
import com.example.movieapp.ui.uiState.ImageUIState
import com.example.movieapp.ui.widget.component.TitleRow
import com.example.movieapp.ui.widget.lazyComponent.DefaultLazyRow
import com.example.movieapp.ui.widget.listItems.LastItemCard
import com.example.movieapp.ui.widget.shimmer.ShimmerMovieRow
import com.example.network.model.image.Poster

@Composable
fun RenderImagesStateRow(
    state: ImageUIState,
    title: String,
    onClick: (Poster) -> Unit,
    onShowAll: () -> Unit
) {
    when (state) {
        ImageUIState.Loading -> ShimmerMovieRow()
        is ImageUIState.Success -> {
            MainImageContent(
                title = title,
                list = state.data,
                onClick = onClick,
                onShowAll = onShowAll
            )
        }
    }
}

@Composable
private fun MainImageContent(
    title: String,
    list: List<Poster>,
    onClick: (Poster) -> Unit,
    onShowAll: () -> Unit
) {
    TitleRow(
        title = title,
        onClick = onShowAll
    )

    DefaultLazyRow(
        list = list,
        key = { it },
        lastItemCard = {
            LastItemCard(
                height = 130.dp,
                width = 90.dp,
                onClick = onShowAll
            )
        }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(it.url)
                .crossfade(true)
                .build(),
            contentDescription = null,
            error = painterResource(R.drawable.ic_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(130.dp)
                .wrapContentWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable { onClick(it) }
        )
    }
}