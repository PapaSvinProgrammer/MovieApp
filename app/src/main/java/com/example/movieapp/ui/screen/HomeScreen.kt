package com.example.movieapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.ui.screen.uiState.CollectionUIState
import com.example.movieapp.ui.screen.uiState.MovieUIState
import com.example.movieapp.ui.viewModel.HomeViewModel
import com.example.movieapp.ui.widget.componentRow.CollectionLazyRow
import com.example.movieapp.ui.widget.componentRow.MovieLazyRow
import com.example.movieapp.ui.widget.componentRow.TitleRow
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
//            item {
//            viewModel.getMoviesDrama()
//                RenderMovieState(
//                    state = viewModel.movieDramaState,
//                    title = "Драмы"
//                )
//            }
//
//            item {
//                viewModel.getMoviesBest250()
//                RenderMovieState(
//                    state = viewModel.movieBest250State,
//                    title = "Собственные рекомендации"
//                )
//            }
//
//            item {
//            viewModel.getMoviesBoevik()
//                RenderMovieState(
//                    state = viewModel.movieBoevikState,
//                    title = "Боевики"
//                )
//            }
//            item {
//            viewModel.getMoviesBest100()
//                RenderMovieState(
//                   state =  viewModel.movieBest100State,
//                    title = "Научная фантастика"
//                )
//            }
//
//            item {
//                viewModel.getMoviesBest501()
//                RenderMovieState(
//                    state = viewModel.movieBest501State,
//                    title = "Стоит посмотреть"
//                )
//            }
//
//            item {
//                viewModel.getCollections()
//                RenderCollectionState(
//                    state = viewModel.collectionState,
//                    title = stringResource(R.string.collections)
//                )
//            }
//
//            item {
//                viewModel.getMoviesHBO()
//                RenderMovieState(
//                    state = viewModel.movieHBOState,
//                    title = "Снято HBO"
//                )
//            }
//
//            item {
//                viewModel.getMoviesNetflix()
//                RenderMovieState(
//                    state = viewModel.movieNetflixState,
//                    title = "Снято Netflix"
//                )
//                Spacer(modifier = Modifier.height(90.dp))
//            }
        }
    }
}

@Composable
private fun RenderMovieState(
    state: MovieUIState,
    title: String
) {
    when (state) {
        is MovieUIState.Success -> MainMovieRow(title, state.data)
        else -> ShimmerMovieRow()
    }
}

@Composable
private fun RenderCollectionState(
    state: CollectionUIState,
    title: String
) {
    when (state) {
        is CollectionUIState.Success -> MainCollectionRow(state.data, title)
        else -> ShimmerCollectionRow()
    }
}

@Composable
private fun MainCollectionRow(
    collections: List<Collection>,
    title: String
) {
    TitleRow(
        title = title,
        onClick = {}
    )

    CollectionLazyRow(
        list = collections,
        onClick = {},
        onShowAll = {}
    )
}

@Composable
private fun MainMovieRow(title: String, movies: List<Movie>) {
    TitleRow(
        title = title,
        onClick = {}
    )

    MovieLazyRow(
        movies = movies,
        onClick = { },
        onShowAll = { }
    )
}