package com.example.movieScreen.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.corecomponent.AppComponent
import com.example.movieScreen.di.DaggerMovieComponent
import com.example.movieScreen.presentation.movie.MovieScreen
import com.example.movieScreen.presentation.movie.MovieViewModel
import com.example.movieScreen.presentation.watchability.WatchabilityListScreen
import com.example.navigationroute.CustomNavType
import com.example.navigationroute.MovieRoutes
import com.example.navigationroute.model.WatchabilityScreenObject
import dev.chrisbanes.haze.HazeState
import kotlin.reflect.typeOf

fun NavGraphBuilder.movieDestination(
    appComponent: AppComponent,
    navController: NavController,
    hazeState: HazeState
) {
    composable<MovieRoutes.MovieRoute> {
        val route = it.toRoute<MovieRoutes.MovieRoute>()
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

    composable<MovieRoutes.WatchabilityListRoute>(
        typeMap = mapOf(
            typeOf<WatchabilityScreenObject>() to CustomNavType.WatchabilityType
        )
    ) {
        val route = it.toRoute<MovieRoutes.WatchabilityListRoute>()

        WatchabilityListScreen(
            navController = navController,
            watchability = route.watchability
        )
    }
}
