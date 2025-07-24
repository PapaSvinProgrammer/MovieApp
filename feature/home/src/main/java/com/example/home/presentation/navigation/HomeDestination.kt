package com.example.home.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.home.di.DaggerHomeComponent
import com.example.home.presentation.HomeDetailListScreen
import com.example.home.presentation.HomeScreen
import com.example.home.presentation.HomeViewModel
import com.example.movielistviewmodel.MovieListViewModel
import com.example.movielistviewmodel.di.DaggerMovieListComponent
import com.example.navigationroute.CustomNavType
import com.example.navigationroute.HomeDetailListRoute
import com.example.navigationroute.HomeRoute
import dev.chrisbanes.haze.HazeState
import kotlin.reflect.typeOf

fun NavGraphBuilder.homeDestination(
    navController: NavController,
    hazeState: HazeState
) {
    composable<HomeRoute> {
        val component = DaggerHomeComponent
            .factory()
            .create()

        val viewModel: HomeViewModel = viewModel(
            factory = component.viewModelFactory
        )

        HomeScreen(
            navController = navController,
            hazeState = hazeState,
            viewModel = viewModel
        )
    }

    composable<HomeDetailListRoute>(
        typeMap = mapOf(
            typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListType
        )
    ) {
        val route = it.toRoute<HomeDetailListRoute>()
        val component = DaggerMovieListComponent
            .factory()
            .create()

        val viewModel: MovieListViewModel = viewModel(
            factory = component.viewModelFactory
        )

        HomeDetailListScreen(
            navController = navController,
            viewModel = viewModel,
            hazeState = hazeState,
            title = route.title,
            queryParameters = route.queryParameters
        )
    }
}