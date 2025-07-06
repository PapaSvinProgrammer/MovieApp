package com.example.search.searchResult

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
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
import com.example.movieapp.search.R
import com.example.navigationroute.MovieRoute
import com.example.search.widget.RenderMovieState
import com.example.ui.widget.other.TitleTopBarText
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