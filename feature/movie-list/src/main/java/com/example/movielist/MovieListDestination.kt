package com.example.movielist

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.movielistviewmodel.MovieListViewModel
import com.example.movielistviewmodel.di.DaggerMovieListComponent
import com.example.movielistviewmodel.di.MovieListDependency
import com.example.navigationroute.CustomNavType
import com.example.navigationroute.MovieListRoute
import dev.chrisbanes.haze.HazeState
import kotlin.reflect.typeOf

fun NavGraphBuilder.movieListDestination(
    dependency: MovieListDependency,
    navController: NavController,
    hazeState: HazeState
) {
    composable<MovieListRoute>(
        typeMap = mapOf(
            typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListTypePair
        )
    ) {
        val route = it.toRoute<MovieListRoute>()
        val component = DaggerMovieListComponent
            .factory()
            .create(dependency)

        val viewModel: MovieListViewModel = viewModel(
            factory = component.viewModelFactory
        )

        MovieListScreen(
            navController = navController,
            viewModel = viewModel,
            hazeState = hazeState,
            title = route.title,
            queryParameters = route.queryParameters
        )
    }
}
