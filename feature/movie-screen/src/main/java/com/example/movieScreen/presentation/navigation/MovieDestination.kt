package com.example.movieScreen.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.movieScreen.di.DaggerMovieComponent
import com.example.movieScreen.di.MovieDependency
import com.example.movieScreen.presentation.groupPerson.GroupPersonViewModel
import com.example.movieScreen.presentation.groupPerson.GroupPersonsScreen
import com.example.movieScreen.presentation.movie.MovieScreen
import com.example.movieScreen.presentation.movie.MovieViewModel
import com.example.movieScreen.presentation.watchability.WatchabilityListScreen
import com.example.navigationroute.MovieRoutes
import com.example.navigationroute.customType
import com.example.navigationroute.model.PersonMovieScreenObject
import com.example.navigationroute.model.WatchabilityScreenObject
import dev.chrisbanes.haze.HazeState
import kotlin.reflect.typeOf

fun NavGraphBuilder.movieDestination(
    movieDependency: MovieDependency,
    navController: NavController,
    hazeState: HazeState
) {
    composable<MovieRoutes.MovieRoute> {
        val route = it.toRoute<MovieRoutes.MovieRoute>()
        val component = DaggerMovieComponent
            .factory()
            .create(movieDependency)

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
            typeOf<WatchabilityScreenObject>() to customType<WatchabilityScreenObject>()
        )
    ) {
        val route = it.toRoute<MovieRoutes.WatchabilityListRoute>()

        WatchabilityListScreen(
            navController = navController,
            watchability = route.watchability
        )
    }

    composable<MovieRoutes.GroupPersonRoute>(
        typeMap = mapOf(
            typeOf<List<PersonMovieScreenObject>>() to customType<List<PersonMovieScreenObject>>()
        )
    ) {
        val component = DaggerMovieComponent
            .factory()
            .create(movieDependency)
        val route = it.toRoute<MovieRoutes.GroupPersonRoute>()
        val viewModel: GroupPersonViewModel = viewModel(
            factory = component.viewModelFactory
        )

        GroupPersonsScreen(
            navController = navController,
            viewModel = viewModel,
            list = route.persons
        )
    }
}
