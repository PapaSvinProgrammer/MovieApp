package com.example.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.home.utils.NavigationUtils
import com.example.home.utils.NavigationUtils.navigateToHomeDetailByGenre
import com.example.home.utils.NavigationUtils.navigateToHomeDetailByLists
import com.example.home.utils.NavigationUtils.navigateToHomeDetailByNetwork
import com.example.home.utils.NavigationUtils.navigateToMovieFromCollection
import com.example.movieapp.home.R
import com.example.navigationroute.CollectionListRoute
import com.example.navigationroute.MovieRoute
import com.example.ui.widget.other.TopBarIconApp
import com.example.ui.widget.renderState.RenderCollectionStateRow
import com.example.ui.widget.renderState.RenderMovieStateRow
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    hazeState: HazeState,
    viewModel: HomeViewModel
) {
    val movieDramaState by viewModel.movieDramaState.collectAsStateWithLifecycle()
    val movieBoevikState by viewModel.movieBoevikState.collectAsStateWithLifecycle()
    val movieBest250State by viewModel.movieBest250State.collectAsStateWithLifecycle()
    val movieBest501State by viewModel.movieBest501State.collectAsStateWithLifecycle()
    val movieBest100State by viewModel.movieBest100State.collectAsStateWithLifecycle()
    val movieHBOState by viewModel.movieHBOState.collectAsStateWithLifecycle()
    val movieNetflixState by viewModel.movieNetflixState.collectAsStateWithLifecycle()
    val collectionState by viewModel.collectionState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarIconApp()
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .hazeSource(hazeState),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                viewModel.getCollections()
                RenderCollectionStateRow(
                    state = collectionState,
                    title = stringResource(R.string.collections),
                    onClick = {
                        navController.navigateToMovieFromCollection(it)
                    },
                    onShowAll = {
                        navController.navigate(CollectionListRoute()) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            item {
                val title = "Драмы"
                viewModel.getMoviesDrama()
                RenderMovieStateRow(
                    state = movieDramaState,
                    title = title,
                    onClick = { navController.navigate(MovieRoute(it.id)) },
                    onShowAll = {
                        navController.navigateToHomeDetailByGenre(
                            title = title,
                            slug = NavigationUtils.DRAMA_GENRE
                        )
                    }
                )
            }

            item {
                val title = "Собственные рекомендации"
                viewModel.getMoviesBest250()
                RenderMovieStateRow(
                    state = movieBest250State,
                    title = title,
                    onClick = { navController.navigate(MovieRoute(it.id)) },
                    onShowAll = {
                        navController.navigateToHomeDetailByLists(
                            title = title,
                            slug = NavigationUtils.LIST250
                        )
                    }
                )
            }

            item {
                val title = "Боевики"
                viewModel.getMoviesBoevik()
                RenderMovieStateRow(
                    state = movieBoevikState,
                    title = title,
                    onClick = { navController.navigate(MovieRoute(it.id)) },
                    onShowAll = {
                        navController.navigateToHomeDetailByGenre(
                            title = title,
                            slug = NavigationUtils.BOEVIK_GENRE
                        )
                    }
                )
            }

            item {
                val title = "Научная фантастика"
                viewModel.getMoviesBest100()
                RenderMovieStateRow(
                    state = movieBest100State,
                    title = title,
                    onClick = { navController.navigate(MovieRoute(it.id)) },
                    onShowAll = {
                        navController.navigateToHomeDetailByLists(
                            title = title,
                            slug = NavigationUtils.LIST_SCIFI
                        )
                    }
                )
            }

            item {
                val title = "Стоит посмотреть"
                viewModel.getMoviesBest501()
                RenderMovieStateRow(
                    state = movieBest501State,
                    title = title,
                    onClick = { navController.navigate(MovieRoute(it.id)) },
                    onShowAll = {
                        navController.navigateToHomeDetailByLists(
                            title = title,
                            slug = NavigationUtils.LIST501
                        )
                    }
                )
            }

            item {
                val title = "Снято HBO"
                viewModel.getMoviesHBO()
                RenderMovieStateRow(
                    state = movieHBOState,
                    title = title,
                    onClick = { navController.navigate(MovieRoute(it.id)) },
                    onShowAll = {
                        navController.navigateToHomeDetailByNetwork(
                            title = title,
                            slug = NavigationUtils.HBO
                        )
                    }
                )
            }

            item {
                val title = "Снято Netflix"
                viewModel.getMoviesNetflix()
                RenderMovieStateRow(
                    state = movieNetflixState,
                    title = title,
                    onClick = { navController.navigate(MovieRoute(it.id)) },
                    onShowAll = {
                        navController.navigateToHomeDetailByNetwork(
                            title = title,
                            slug = NavigationUtils.NETFLIX
                        )
                    }
                )
                Spacer(modifier = Modifier.height(90.dp))
            }
        }
    }
}