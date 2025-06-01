package com.example.movieapp.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.ui.screen.uiState.MovieUIState
import com.example.movieapp.ui.viewModel.SearchResultViewModel
import com.example.movieapp.ui.widget.listItams.MovieDetailCard
import com.example.movieapp.ui.widget.other.TitleTopBarText
import com.example.network.module.movie.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultScreen(
    navController: NavController,
    viewModel: SearchResultViewModel,
    queryParameters: Map<String, String>
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
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
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
            modifier = Modifier.padding(innerPadding),
            onClick = {  }
        )
    }
}

@Composable
private fun RenderMovieState(
    modifier: Modifier,
    state: MovieUIState,
    onClick: (Movie) -> Unit
) {
    when (state) {
        MovieUIState.Loading -> {}
        is MovieUIState.Success -> {
            MainContent(
                modifier = modifier,
                movies = state.data,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier,
    movies: List<Movie>,
    onClick: (Movie) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(movies) {
            MovieDetailCard(it) { onClick(it) }
        }
    }
}