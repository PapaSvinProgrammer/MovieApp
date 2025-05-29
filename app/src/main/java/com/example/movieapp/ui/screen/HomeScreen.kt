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
import com.example.movieapp.ui.screen.uiState.CollectionUIState
import com.example.movieapp.ui.screen.uiState.MovieUIState
import com.example.movieapp.ui.viewModel.HomeViewModel
import com.example.movieapp.ui.widget.component.DefaultLazyRow
import com.example.movieapp.ui.widget.component.TitleRow
import com.example.movieapp.ui.widget.listItams.CollectionCard
import com.example.movieapp.ui.widget.listItams.MovieCard
import com.example.movieapp.ui.widget.shimmer.ShimmerCollectionRow
import com.example.movieapp.ui.widget.shimmer.ShimmerMovieRow
import com.example.network.module.image.Collection
import com.example.network.module.movie.Movie
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
            viewModel.getMoviesDrama()
                RenderMovieRowState(
                    state = viewModel.movieDramaState,
                    title = "Драмы",
                    onClick = {},
                    onShowAll = {}
                )
            }

            item {
                viewModel.getMoviesBest250()
                RenderMovieRowState(
                    state = viewModel.movieBest250State,
                    title = "Собственные рекомендации",
                    onClick = {},
                    onShowAll = {}
                )
            }

            item {
                viewModel.getMoviesBoevik()
                RenderMovieRowState(
                    state = viewModel.movieBoevikState,
                    title = "Боевики",
                    onClick = {},
                    onShowAll = {}
                )
            }

            item {
                viewModel.getMoviesBest100()
                RenderMovieRowState(
                    state =  viewModel.movieBest100State,
                    title = "Научная фантастика",
                    onClick = {},
                    onShowAll = {}
                )
            }

            item {
                viewModel.getMoviesBest501()
                RenderMovieRowState(
                    state = viewModel.movieBest501State,
                    title = "Стоит посмотреть",
                    onClick = {},
                    onShowAll = {}
                )
            }

            item {
                viewModel.getCollections()
                RenderCollectionRowState(
                    state = viewModel.collectionState,
                    title = stringResource(R.string.collections),
                    onClick = {},
                    onShowAll = {}
                )
            }

            item {
                viewModel.getMoviesHBO()
                RenderMovieRowState(
                    state = viewModel.movieHBOState,
                    title = "Снято HBO",
                    onClick = {},
                    onShowAll = {}
                )
            }

            item {
                viewModel.getMoviesNetflix()
                RenderMovieRowState(
                    state = viewModel.movieNetflixState,
                    title = "Снято Netflix",
                    onClick = {},
                    onShowAll = {}
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
        onShowAll = onShowAll,
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
        onShowAll = onShowAll,
        content = {
            MovieCard(
                movie = it,
                onClick = { onCLick(it) }
            )
        }
    )
}