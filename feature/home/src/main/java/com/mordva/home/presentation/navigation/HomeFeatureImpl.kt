package com.mordva.home.presentation.navigation

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
import com.mordva.home.di.DaggerHomeComponent
import com.mordva.home.di.HomeDependency
import com.mordva.home.presentation.HomeDetailListScreen
import com.mordva.home.presentation.HomeScreen
import com.mordva.home.presentation.HomeViewModel
import com.mordva.navigation.CustomNavType
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.HomeGraph
import dev.chrisbanes.haze.HazeState
import kotlin.reflect.typeOf

private typealias HomeDetailParams = List<Pair<String, String>>

class HomeFeatureImpl(
    private val homeDependency: HomeDependency,
    private val movieListDependency: MovieListDependency
) : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        hazeState: HazeState,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<HomeGraph>(
            startDestination = HomeRoute
        ) {
            composable<HomeRoute> {
                val component = DaggerHomeComponent.factory().create(homeDependency)
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
                    typeOf<HomeDetailParams>() to CustomNavType.ListTypePair
                )
            ) {
                val component = DaggerMovieListComponent.factory().create(movieListDependency)
                val viewModel: MovieListViewModel = viewModel(
                    factory = component.viewModelFactory
                )

                val route = it.toRoute<HomeDetailListRoute>()

                HomeDetailListScreen(
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