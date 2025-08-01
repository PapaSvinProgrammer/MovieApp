package com.example.movieScreen.presentation.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.model.movie.Movie
import com.example.movieScreen.presentation.movie.widget.collapsingTopBar.BackdropContent
import com.example.movieScreen.presentation.movie.widget.collapsingTopBar.CollapsedTopBar
import com.example.movieScreen.presentation.movie.widget.collapsingTopBar.ExpandedContent
import com.example.movieScreen.presentation.movie.widget.itemContent.collectionsItem
import com.example.movieScreen.presentation.movie.widget.itemContent.commentsItem
import com.example.movieScreen.presentation.movie.widget.itemContent.factsItem
import com.example.movieScreen.presentation.movie.widget.itemContent.imagesItem
import com.example.movieScreen.presentation.movie.widget.itemContent.movieDescriptionItem
import com.example.movieScreen.presentation.movie.widget.itemContent.personGridHorizontalItem
import com.example.movieScreen.presentation.movie.widget.itemContent.premierItem
import com.example.movieScreen.presentation.movie.widget.itemContent.ratingCardLargeItem
import com.example.movieScreen.presentation.movie.widget.itemContent.seasonDescriptionItem
import com.example.movieScreen.presentation.movie.widget.itemContent.sequelsAndPrequelsItem
import com.example.movieScreen.presentation.movie.widget.itemContent.similarMoviesItem
import com.example.movieScreen.presentation.movie.widget.itemContent.supportPersonalItem
import com.example.movieScreen.presentation.movie.widget.itemContent.voiceActorsItem
import com.example.movieScreen.presentation.movie.widget.itemContent.watchabilityItem
import com.example.navigationroute.MovieRoutes
import com.example.navigationroute.model.toScreenObject
import com.example.ui.uiState.MovieUIState
import com.example.ui.widget.bottomSheets.FactSheet
import com.example.ui.widget.component.BasicLoadingBox
import com.example.ui.widget.other.TitleTopBarText
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@Composable
internal fun MovieScreen(
    navController: NavController,
    viewModel: MovieViewModel,
    hazeState: HazeState,
    id: Int
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val scrollState = rememberLazyListState()
    val firstOffset by remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }
    val index by remember { derivedStateOf { scrollState.firstVisibleItemIndex } }
    var isCollapsed by remember { mutableStateOf(false) }

    LaunchedEffect(firstOffset, index) {
        if (index == 0) {
            isCollapsed = firstOffset > 800
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getMovie(id)
        viewModel.getImages(id)
        viewModel.getComments(id)
    }

    LaunchedEffect(uiState.movieState) {
        uiState.movieState.body()?.let {
            viewModel.getCollections(it.lists)
        }
    }

    RenderMovieContent(state = uiState.movieState)

    uiState.movieState.body()?.let { movie ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(hazeState)
        ) {
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
                actions = { }
            )

            LazyColumn(
                modifier = Modifier.hazeSource(hazeState),
                state = scrollState
            ) {
                item { ExpandedContent(movie) }

                movieDescriptionItem(movie)

                seasonDescriptionItem(movie)

                watchabilityItem(movie, navController)

                ratingCardLargeItem(movie)

                personGridHorizontalItem(
                    actors = uiState.actors,
                    navController = navController,
                    onClick = {
                        navController.navigate(
                            MovieRoutes.GroupPersonRoute(movie.persons.toScreenObject())
                        ) { launchSingleTop = true }
                    }
                )

                supportPersonalItem(uiState.supportPersonal, navController)

                voiceActorsItem(uiState.voiceActors, navController)

                commentsItem(uiState.comments)

                imagesItem(uiState.images)

                collectionsItem(
                    data = uiState.collections,
                    navController = navController,
                    listId = movie.lists
                )

                factsItem(movie.facts)

                premierItem(movie.premiere)

                sequelsAndPrequelsItem(movie.sequelsAndPrequels, navController)

                similarMoviesItem(movie.similarMovies, navController)

                item { Spacer(modifier = Modifier.height(130.dp)) }
            }
        }
    }

    if (uiState.selectedFact.isNotEmpty()) {
        FactSheet(
            text = uiState.selectedFact,
            onDismissRequest = {
                viewModel.updateSelectedFact("")
            }
        )
    }
}

private fun MovieUIState.body(): Movie? {
    return (this as? MovieUIState.Success)?.data?.first()
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