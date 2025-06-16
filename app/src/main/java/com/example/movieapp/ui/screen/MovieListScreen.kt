package com.example.movieapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.movieapp.app.navigation.MovieRoute
import com.example.movieapp.ui.uiState.MovieUIState
import com.example.movieapp.ui.widget.lazyComponent.EndlessLazyColumn
import com.example.movieapp.ui.widget.listItems.MovieListCard
import com.example.movieapp.ui.widget.other.TitleTopBarText
import com.example.movieapp.ui.widget.shimmer.ShimmerMovieDetailList
import com.example.movieapp.viewModels.MovieListViewModel
import com.example.network.module.movie.Movie
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel,
    hazeState: HazeState,
    title: String,
    queryParameters: List<Pair<String, String>>
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val isCollapsed by remember {
        derivedStateOf { scrollBehavior.state.collapsedFraction > 0.5 }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getMovies(queryParameters)
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    TopBarText(
                        text = title,
                        isCollapsed = isCollapsed
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                ),
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        RenderResult(
            state = viewModel.moviesState,
            modifier = Modifier.hazeSource(hazeState).padding(innerPadding),
            onClick = {
                navController.navigate(MovieRoute(it.id)) {
                    launchSingleTop = true
                }
            },
            onSettingsClick = {},
            onLoadMore = { viewModel.loadMoreMovies(queryParameters) }
        )
    }
}

@Composable
private fun TopBarText(
    text: String,
    isCollapsed: Boolean
) {
    if (isCollapsed) {
        TitleTopBarText(text)
    }
    else {
        Text(
            text = text,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun RenderResult(
    state: MovieUIState,
    modifier: Modifier,
    onClick: (Movie) -> Unit,
    onSettingsClick: (Movie) -> Unit,
    onLoadMore: () -> Unit
) {
    when (state) {
        MovieUIState.Loading -> ShimmerMovieDetailList(modifier)
        is MovieUIState.Success -> {
            MainPersonContent(
                modifier = modifier,
                list = state.data,
                onClick = onClick,
                onLoadMore = onLoadMore,
                onSettingsClick = onSettingsClick
            )
        }
    }
}

@Composable
private fun MainPersonContent(
    modifier: Modifier = Modifier,
    list: List<Movie>,
    onClick: (Movie) -> Unit,
    onLoadMore: () -> Unit,
    onSettingsClick: (Movie) -> Unit
) {
    EndlessLazyColumn(
        modifier = modifier,
        items = list,
        loadMore = onLoadMore
    ) { _, item ->
        MovieListCard(
            movie = item,
            onClick = { onClick(item) },
            onSettingsClick = { onSettingsClick(item) }
        )

        HorizontalDivider(modifier = Modifier.padding(start = 15.dp))
    }
}