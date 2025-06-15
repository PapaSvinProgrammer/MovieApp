package com.example.movieapp.ui.screen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.app.navigation.CollectionListRoute
import com.example.movieapp.app.navigation.HomeDetailListRoute
import com.example.movieapp.app.navigation.MovieListRoute
import com.example.movieapp.app.navigation.MovieRoute
import com.example.movieapp.viewModels.HomeViewModel
import com.example.movieapp.ui.widget.other.TopBarIconApp
import com.example.movieapp.ui.widget.renderState.RenderCollectionStateRow
import com.example.movieapp.ui.widget.renderState.RenderMovieStateRow
import com.example.network.utils.Constants
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    hazeState: HazeState,
    viewModel: HomeViewModel
) {
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
                    state = viewModel.collectionState,
                    title = stringResource(R.string.collections),
                    onClick = {
                        val query = listOf(
                            Constants.LISTS_FIELD to it.slug.toString(),
                            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                            Constants.SORT_TYPE to Constants.SORT_DESC
                        )

                        navController.navigate(
                            MovieListRoute(
                                title = it.name ?: "",
                                queryParameters = query
                            )
                        )
                    },
                    onShowAll = { navController.navigate(CollectionListRoute()) }
                )
            }

            item {
                val title = "Драмы"
                viewModel.getMoviesDrama()
                RenderMovieStateRow(
                    state = viewModel.movieDramaState,
                    title = title,
                    onClick = { navController.navigate(MovieRoute(it.id)) },
                    onShowAll = {
                        val query = arrayListOf(
                            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                            Constants.SORT_TYPE to Constants.SORT_DESC,
                            Constants.GENRES_NAME_FIELD to "драма"
                        )

                        navController.navigate(
                            HomeDetailListRoute(
                                title = title,
                                queryParameters = query
                            )
                        )
                    }
                )
            }

            item {
                val title = "Собственные рекомендации"
                viewModel.getMoviesBest250()
                RenderMovieStateRow(
                    state = viewModel.movieBest250State,
                    title = title,
                    onClick = { navController.navigate(MovieRoute(it.id)) },
                    onShowAll = {
                        val queryParameters = listOf(
                            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                            Constants.SORT_TYPE to Constants.SORT_DESC,
                            Constants.LISTS_FIELD to "top250"
                        )

                        navController.navigate(
                            HomeDetailListRoute(
                                title = title,
                                queryParameters = queryParameters
                            )
                        )
                    }
                )
            }

            item {
                val title = "Боевики"
                viewModel.getMoviesBoevik()
                RenderMovieStateRow(
                    state = viewModel.movieBoevikState,
                    title = title,
                    onClick = { navController.navigate(MovieRoute(it.id)) },
                    onShowAll = {
                        val query = listOf(
                            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                            Constants.SORT_TYPE to Constants.SORT_DESC,
                            Constants.GENRES_NAME_FIELD to "боевик"
                        )

                        navController.navigate(
                            HomeDetailListRoute(
                                title = title,
                                queryParameters = query
                            )
                        )
                    }
                )
            }

            item {
                val title = "Научная фантастика"
                viewModel.getMoviesBest100()
                RenderMovieStateRow(
                    state =  viewModel.movieBest100State,
                    title = title,
                    onClick = { navController.navigate(MovieRoute(it.id)) },
                    onShowAll = {
                        val queryParameters = listOf(
                            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                            Constants.SORT_TYPE to Constants.SORT_DESC,
                            Constants.LISTS_FIELD to "top_100_scifi_by_total_scifi_online"
                        )

                        navController.navigate(
                            HomeDetailListRoute(
                                title = title,
                                queryParameters = queryParameters
                            )
                        )
                    }
                )
            }

            item {
                val title = "Стоит посмотреть"
                viewModel.getMoviesBest501()
                RenderMovieStateRow(
                    state = viewModel.movieBest501State,
                    title = title,
                    onClick = { navController.navigate(MovieRoute(it.id)) },
                    onShowAll = {
                        val queryParameters = listOf(
                            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                            Constants.SORT_TYPE to Constants.SORT_DESC,
                            Constants.LISTS_FIELD to "best_501"
                        )

                        navController.navigate(
                            HomeDetailListRoute(
                                title = title,
                                queryParameters = queryParameters
                            )
                        )
                    }
                )
            }

            item {
                val title = "Снято HBO"
                viewModel.getMoviesHBO()
                RenderMovieStateRow(
                    state = viewModel.movieHBOState,
                    title = title,
                    onClick = { navController.navigate(MovieRoute(it.id)) },
                    onShowAll = {
                        val queryParameters = listOf(
                            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                            Constants.SORT_TYPE to Constants.SORT_DESC,
                            Constants.NETWORK_ITEMS_NAME to "HBO"
                        )

                        navController.navigate(
                            HomeDetailListRoute(
                                title = title,
                                queryParameters = queryParameters
                            )
                        )
                    }
                )
            }

            item {
                val title = "Снято Netflix"
                viewModel.getMoviesNetflix()
                RenderMovieStateRow(
                    state = viewModel.movieNetflixState,
                    title = title,
                    onClick = { navController.navigate(MovieRoute(it.id)) },
                    onShowAll = {
                        val queryParameters = listOf(
                            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                            Constants.SORT_TYPE to Constants.SORT_DESC,
                            Constants.NETWORK_ITEMS_NAME to "Netflix"
                        )

                        navController.navigate(
                            HomeDetailListRoute(
                                title = title,
                                queryParameters = queryParameters
                            )
                        )
                    }
                )
                Spacer(modifier = Modifier.height(90.dp))
            }
        }
    }
}