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
import com.example.movieapp.ui.screen.uiState.CollectionUIState
import com.example.movieapp.ui.screen.uiState.MovieUIState
import com.example.movieapp.viewModels.HomeViewModel
import com.example.movieapp.ui.widget.lazyComponent.DefaultLazyRow
import com.example.movieapp.ui.widget.component.TitleRow
import com.example.movieapp.ui.widget.listItems.CollectionCard
import com.example.movieapp.ui.widget.listItems.LastItemCard
import com.example.movieapp.ui.widget.listItems.MovieCard
import com.example.movieapp.ui.widget.other.TopBarIconApp
import com.example.movieapp.ui.widget.shimmer.ShimmerCollectionRow
import com.example.movieapp.ui.widget.shimmer.ShimmerMovieRow
import com.example.network.module.image.Collection
import com.example.network.module.movie.Movie
import com.example.network.utils.Constants
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

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
                .haze(hazeState),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                viewModel.getCollections()
                RenderCollectionRowState(
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
                RenderMovieRowState(
                    state = viewModel.movieDramaState,
                    title = title,
                    onClick = {},
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
                RenderMovieRowState(
                    state = viewModel.movieBest250State,
                    title = title,
                    onClick = {},
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
                RenderMovieRowState(
                    state = viewModel.movieBoevikState,
                    title = title,
                    onClick = {},
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
                RenderMovieRowState(
                    state =  viewModel.movieBest100State,
                    title = title,
                    onClick = {},
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
                RenderMovieRowState(
                    state = viewModel.movieBest501State,
                    title = title,
                    onClick = {},
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
                RenderMovieRowState(
                    state = viewModel.movieHBOState,
                    title = title,
                    onClick = {},
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
                RenderMovieRowState(
                    state = viewModel.movieNetflixState,
                    title = title,
                    onClick = {},
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

@Composable
fun RenderMovieRowState(
    state: MovieUIState,
    title: String,
    onClick: (Movie) -> Unit,
    onShowAll: () -> Unit
) {
    when (state) {
        is MovieUIState.Success -> {
            MainMovieRow(
                title = title,
                movies = state.data,
                onCLick = onClick,
                onShowAll = onShowAll
            )
        }
        else -> ShimmerMovieRow()
    }
}

@Composable
fun RenderCollectionRowState(
    state: CollectionUIState,
    title: String,
    onClick: (Collection) -> Unit,
    onShowAll: () -> Unit
) {
    when (state) {
        is CollectionUIState.Success -> {
            MainCollectionRow(
                collections = state.data,
                title = title,
                onClick = onClick,
                onShowAll = onShowAll
            )
        }
        else -> ShimmerCollectionRow()
    }
}

@Composable
private fun MainCollectionRow(
    collections: List<Collection>,
    title: String,
    onClick: (Collection) -> Unit,
    onShowAll: () -> Unit
) {
    TitleRow(
        title = title,
        onClick = onShowAll
    )

    DefaultLazyRow(
        list = collections,
        key = { it.id },
        lastItemCard = {
            LastItemCard(
                width = 140.dp,
                height = 140.dp,
                onClick = onShowAll
            )
        },
        content = {
            CollectionCard(
                image = it.cover?.url ?: "",
                title = it.name ?: "",
                onClick = { onClick(it) }
            )
        }
    )
}

@Composable
private fun MainMovieRow(
    title: String,
    movies: List<Movie>,
    onCLick: (Movie) -> Unit,
    onShowAll: () -> Unit
) {
    TitleRow(
        title = title,
        onClick = onShowAll
    )

    DefaultLazyRow(
        list = movies,
        key = { it.id },
        lastItemCard = {
            LastItemCard(
                width = 160.dp,
                height = 260.dp,
                onClick = {

                }
            )
        },
        content = {
            MovieCard(
                movie = it,
                onClick = { onCLick(it) }
            )
        }
    )
}