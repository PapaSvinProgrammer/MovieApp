package com.mordva.movielist.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.base_view_models.movie_list.DaggerMovieListComponent
import com.mordva.base_view_models.movie_list.MovieListDependency
import com.mordva.base_view_models.movie_list.MovieListViewModel
import com.mordva.movielist.MovieListScreen
import com.mordva.navigation.CustomNavType
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.MovieListGraph
import dev.chrisbanes.haze.HazeState
import kotlin.reflect.typeOf

class MovieListFeatureImpl(
    private val dependency: MovieListDependency
) : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        hazeState: HazeState,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<MovieListGraph>(
            startDestination = MovieListGraph.MovieListRoute("", listOf())
        ) {
            composable<MovieListGraph.MovieListRoute>(
                typeMap = mapOf(
                    typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListTypePair
                )
            ) {
                val route = it.toRoute<MovieListGraph.MovieListRoute>()

                val component = DaggerMovieListComponent.factory().create(dependency)
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
    }
}