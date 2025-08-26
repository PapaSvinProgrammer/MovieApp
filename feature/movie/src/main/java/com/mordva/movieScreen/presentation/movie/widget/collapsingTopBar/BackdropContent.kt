package com.mordva.movieScreen.presentation.movie.widget.collapsingTopBar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.mordva.model.movie.Movie
import com.example.movieapp.ui.R
import com.mordva.ui.widget.component.FadingDefaults
import com.mordva.ui.widget.component.fadingEdge

@Composable
internal fun BackdropContent(scrollState: LazyListState, movie: Movie) {
    val alpha by remember {
        derivedStateOf {
            if (scrollState.firstVisibleItemIndex > 0) {
                0f
            } else {
                1f - (scrollState.firstVisibleItemScrollOffset / 600f).coerceIn(0f, 1f)
            }
        }
    }

    if (movie.backdrop?.url.isCorrectUrl() && movie.logo?.url.isCorrectUrl()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.backdrop?.url)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.ic_movie),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fadingEdge(FadingDefaults.bottomFade)
                .height(350.dp)
                .graphicsLayer { this.alpha = alpha }
        )
    }
}

fun String?.isCorrectUrl(): Boolean {
    return this != null
}