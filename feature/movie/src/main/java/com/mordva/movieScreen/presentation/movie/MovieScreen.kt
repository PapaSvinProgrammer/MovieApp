package com.mordva.movieScreen.presentation.movie

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mordva.model.movie.Movie
import com.mordva.movieScreen.presentation.movie.widget.collapsingTopBar.BackdropContent
import com.mordva.movieScreen.presentation.movie.widget.collapsingTopBar.CollapsedTopBar
import com.mordva.movieScreen.presentation.movie.widget.collapsingTopBar.ExpandedContent
import com.mordva.movieScreen.presentation.movie.widget.itemContent.collectionsItem
import com.mordva.movieScreen.presentation.movie.widget.itemContent.commentsItem
import com.mordva.movieScreen.presentation.movie.widget.itemContent.factsItem
import com.mordva.movieScreen.presentation.movie.widget.itemContent.imagesItem
import com.mordva.movieScreen.presentation.movie.widget.itemContent.movieDescriptionItem
import com.mordva.movieScreen.presentation.movie.widget.itemContent.personGridHorizontalItem
import com.mordva.movieScreen.presentation.movie.widget.itemContent.premierItem
import com.mordva.movieScreen.presentation.movie.widget.itemContent.ratingCardLargeItem
import com.mordva.movieScreen.presentation.movie.widget.itemContent.seasonDescriptionItem
import com.mordva.movieScreen.presentation.movie.widget.itemContent.sequelsAndPrequelsItem
import com.mordva.movieScreen.presentation.movie.widget.itemContent.similarMoviesItem
import com.mordva.movieScreen.presentation.movie.widget.itemContent.supportPersonalItem
import com.mordva.movieScreen.presentation.movie.widget.itemContent.voiceActorsItem
import com.mordva.movieScreen.presentation.movie.widget.itemContent.watchabilityItem
import com.mordva.movieScreen.presentation.navigation.GroupPersonRoute
import com.mordva.movieScreen.utils.toScreenObject
import com.mordva.ui.uiState.MovieUIState
import com.mordva.ui.widget.bottomSheets.FactSheet
import com.mordva.ui.widget.component.BasicLoadingBox
import com.mordva.ui.widget.other.TitleTopBarText
import com.mordva.ui.widget.scoreBottomSheet.ScoreBottomSheet
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@Composable
internal fun MovieScreen(
    navController: NavController,
    viewModel: MovieViewModel,
    hazeState: HazeState,
    id: Int
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val scrollState = rememberLazyListState()
    val firstOffset by remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }
    val index by remember { derivedStateOf { scrollState.firstVisibleItemIndex } }

    LaunchedEffect(firstOffset, index) {
        if (index == 0) {
            viewModel.updateCollapsedState(firstOffset > 800)
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getMovie(id)
        viewModel.getImages(id)
        viewModel.getComments(id)
    }

    LaunchedEffect(state.movieState) {
        state.movieState.body().let {
            viewModel.isRatedMovie()
            viewModel.getCollections(it.lists)
        }
    }

    RenderMovieContent(state = state.movieState)

    state.movieState.body().let { movie ->
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
                isCollapsed = state.isCollapsed,
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
                item {
                    ExpandedContent(
                        movie = movie,
                        customRating = state.isRatedMovieState?.rating,
                        onEvaluate = {
                            viewModel.updateScoreSheetVisible(true)
                        },
                        onAddIntoFuturePackage = {},
                        onShare = {},
                        onMore = {}
                    )
                }

                movieDescriptionItem(movie)

                seasonDescriptionItem(movie)

                watchabilityItem(movie, navController)

                ratingCardLargeItem(movie)

                personGridHorizontalItem(
                    actors = state.actors,
                    navController = navController,
                    onClick = {
                        navController.navigate(
                            GroupPersonRoute(movie.persons.toScreenObject())
                        ) { launchSingleTop = true }
                    }
                )

                supportPersonalItem(state.supportPersonal, navController)

                voiceActorsItem(state.voiceActors, navController)

                commentsItem(state.comments)

                imagesItem(state.images)

                collectionsItem(
                    data = state.collections,
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

    if (state.selectedFact.isNotEmpty()) {
        FactSheet(
            text = state.selectedFact,
            onDismissRequest = {
                viewModel.updateSelectedFact("")
            }
        )
    }

    if (state.scoreSheetVisible) {
        ScoreBottomSheet(
            movie = state.movieState.body(),
            initialValue = state.isRatedMovieState?.rating,
            ratedMovieState = state.ratedMoviesState,
            onAction = { action ->
                viewModel.handleScoreSheetHandle(action)
            },
            onDismissRequest = {
                viewModel.updateScoreSheetVisible(false)
            }
        )
    }
}

internal fun MovieUIState.body(): Movie {
    return (this as? MovieUIState.Success)?.data?.first() ?: Movie()
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