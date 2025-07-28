package com.example.movieScreen.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.corecomponent.AppComponent
import com.example.movieScreen.di.DaggerMovieComponent
import com.example.movieScreen.presentation.MovieScreen
import com.example.movieScreen.presentation.MovieViewModel
import com.example.navigationroute.MovieRoute
import dev.chrisbanes.haze.HazeState

fun NavGraphBuilder.movieDestination(
    appComponent: AppComponent,
    navController: NavController,
    hazeState: HazeState
) {
    composable<MovieRoute> {
        val route = it.toRoute<MovieRoute>()
        val component = DaggerMovieComponent.factory().create(appComponent)

        val viewModel: MovieViewModel = viewModel(
            factory = component.viewModelFactory
        )

        MovieScreen(
            navController = navController,
            viewModel = viewModel,
            hazeState = hazeState,
            id = route.id
        )
    }
}