package com.example.movieapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.movieapp.R
import com.example.movieapp.app.utils.FadingDefaults
import com.example.movieapp.app.utils.fadingEdge
import com.example.movieapp.ui.screen.uiState.MovieUIState
import com.example.movieapp.ui.widget.collapsingTopBar.CollapsedTopBar
import com.example.movieapp.ui.widget.collapsingTopBar.ExpandedContent
import com.example.movieapp.ui.widget.component.BasicLoadingBox
import com.example.movieapp.ui.widget.other.TitleTopBarText
import com.example.movieapp.viewModels.MovieViewModel
import com.example.network.module.movie.Movie
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MovieViewModel,
    hazeState: HazeState,
    id: Int
) {
    val scrollState = rememberLazyListState()
    val firstOffset by remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }
    val index by remember { derivedStateOf { scrollState.firstVisibleItemIndex } }
    var isCollapsed by remember { mutableStateOf(false) }

    LaunchedEffect(firstOffset, index) {
        if (index == 0) {
            if (firstOffset > 800) {
                isCollapsed = true
            }
            else {
                isCollapsed = false
            }
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getMovie(id)
    }

    RenderMovieContent(state = viewModel.movieState)

    (viewModel.movieState as? MovieUIState.Success)?.data?.first()?.let { movie ->
        Box(modifier = Modifier.fillMaxSize()) {
            BackdropContent(
                scrollState = scrollState,
                movie = movie
            )

            CollapsedTopBar(
                isCollapsed = isCollapsed,
                title = { TitleTopBarText(text = movie.name ?: "") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {}
            )

            LazyColumn(
                modifier = Modifier.hazeSource(hazeState),
                state = scrollState
            ) {
                item {
                    ExpandedContent(movie)
                }

                items(100) {
                    ListItem(
                        headlineContent = { Text(text = "text $it") }
                    )
                }
            }
        }
    }
}

@Composable
private fun RenderMovieContent(
    modifier: Modifier = Modifier,
    state: MovieUIState
) {
    when (state) {
        MovieUIState.Loading -> BasicLoadingBox(modifier)
        is MovieUIState.Success -> {}
    }
}

@Composable
fun BackdropContent(scrollState: LazyListState, movie: Movie) {
    val alpha by remember {
        derivedStateOf {
            if (scrollState.firstVisibleItemIndex > 0) {
                0f
            } else {
                1f - (scrollState.firstVisibleItemScrollOffset / 600f).coerceIn(0f, 1f)
            }
        }
    }

    if (movie.backdrop?.url.isCorrectUrl()) {
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

internal fun String?.isCorrectUrl(): Boolean {
    return this != null && this != "https://image.openmoviedb.com/kinopoisk-ott-images/2385704/2a00000194b0fed07179b4dc6e80a80f4afd/orig"
}