package com.example.movieapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.app.navigation.MovieRoute
import com.example.movieapp.ui.uiState.MovieUIState
import com.example.movieapp.ui.widget.lazyComponent.EndlessLazyColumn
import com.example.movieapp.ui.widget.listItems.MovieDetailCard
import com.example.movieapp.ui.widget.other.TitleTopBarText
import com.example.movieapp.ui.widget.shimmer.ShimmerMovieDetailList
import com.example.movieapp.viewModels.SearchResultViewModel
import com.example.network.module.movie.Movie
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultScreen(
    navController: NavController,
    viewModel: SearchResultViewModel,
    hazeState: HazeState,
    queryParameters: List<Pair<String, String>>
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(R.string.search_result))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LifecycleEventEffect(Lifecycle.Event.ON_START) {
            viewModel.getMovies(queryParameters)
        }

        RenderMovieState(
            state = viewModel.movieUIState,
            modifier = Modifier.padding(innerPadding).hazeSource(hazeState),
            onClick = {
                navController.navigate(MovieRoute(it.id)) {
                    launchSingleTop = true
                }
            },
            loadMore = { viewModel.loadMoreMovies(queryParameters) }
        )
    }
}

@Composable
private fun RenderMovieState(
    modifier: Modifier,
    state: MovieUIState,
    onClick: (Movie) -> Unit,
    loadMore: () -> Unit
) {
    when (state) {
        is MovieUIState.Success -> {
            MainPersonContent(
                modifier = modifier,
                movies = state.data,
                onClick = onClick,
                loadMore = loadMore
            )
        }
        else -> ShimmerMovieDetailList(modifier)
    }
}

@Composable
private fun MainPersonContent(
    modifier: Modifier,
    movies: List<Movie>,
    onClick: (Movie) -> Unit,
    loadMore: () -> Unit
) {

    EndlessLazyColumn(
        modifier = modifier,
        items = movies,
        loadMore = loadMore
    ) { _, it ->
        MovieDetailCard(it) { onClick(it) }
        HorizontalDivider(modifier = Modifier.padding(start = 110.dp))
    }
}