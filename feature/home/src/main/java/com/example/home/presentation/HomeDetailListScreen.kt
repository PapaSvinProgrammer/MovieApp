package com.example.home.presentation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.model.movie.Movie
import com.example.movielistviewmodel.MovieListViewModel
import com.example.navigationroute.MovieRoute
import com.example.ui.uiState.MovieUIState
import com.example.ui.widget.component.LoadingSearchContent
import com.example.ui.widget.lazyComponent.EndlessLazyVerticalGrid
import com.example.ui.widget.listItems.MovieCard
import com.example.ui.widget.other.TitleTopBarText
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDetailListScreen(
    navController: NavController,
    viewModel: MovieListViewModel,
    hazeState: HazeState,
    title: String,
    queryParameters: List<Pair<String, String>>
) {
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getMovies(queryParameters)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleTopBarText(text = title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        RenderMovieState(
            modifier = Modifier.padding(innerPadding).hazeSource(hazeState),
            state = viewModel.moviesState,
            onLoadMore = { viewModel.loadMoreMovies(queryParameters) },
            onClick = {
                navController.navigate(MovieRoute(it.id)) { launchSingleTop = true }
            }
        )
    }
}

@Composable
private fun RenderMovieState(
    state: MovieUIState,
    modifier: Modifier,
    onLoadMore: () -> Unit,
    onClick: (Movie) -> Unit
) {
    when (state) {
        MovieUIState.Loading -> LoadingSearchContent()
        is MovieUIState.Success -> MainPersonContent(
            list = state.data,
            modifier = modifier,
            onLoadMore = onLoadMore,
            onClick = onClick
        )
    }
}

@Composable
private fun MainPersonContent(
    list: List<Movie>,
    modifier: Modifier,
    onLoadMore: () -> Unit,
    onClick: (Movie) -> Unit
) {
    EndlessLazyVerticalGrid(
        modifier = modifier,
        list = list,
        onLoadMore = onLoadMore
    ) {
        MovieCard(
            movie = it,
            modifier = Modifier.height(300.dp),
            onClick = { onClick(it) }
        )
    }
}