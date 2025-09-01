package com.mordva.movieScreen.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.movieScreen.di.DaggerMovieComponent
import com.mordva.movieScreen.di.MovieDependency
import com.mordva.movieScreen.domain.model.PersonMovieScreenObject
import com.mordva.movieScreen.utils.PersonMovieListScreenObjectType
import com.mordva.movieScreen.utils.WatchabilityType
import com.mordva.movieScreen.domain.model.WatchabilityScreenObject
import com.mordva.movieScreen.presentation.groupPerson.GroupPersonViewModel
import com.mordva.movieScreen.presentation.groupPerson.GroupPersonsScreen
import com.mordva.movieScreen.presentation.movie.MovieScreen
import com.mordva.movieScreen.presentation.movie.MovieViewModel
import com.mordva.movieScreen.presentation.watchability.WatchabilityListScreen
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.MovieGraph
import dev.chrisbanes.haze.HazeState
import kotlin.reflect.typeOf

class MovieFeatureImpl(
    private val dependency: MovieDependency
) : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        hazeState: HazeState,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<MovieGraph>(
            startDestination = MovieGraph.MovieRoute(326)
        ) {
            composable<MovieGraph.MovieRoute> {
                val route = it.toRoute<MovieGraph.MovieRoute>()

                val component = DaggerMovieComponent.factory().create(dependency)
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

            composable<WatchabilityListRoute>(
                typeMap = mapOf(
                    typeOf<WatchabilityScreenObject>() to WatchabilityType
                )
            ) {
                val route = it.toRoute<WatchabilityListRoute>()

                WatchabilityListScreen(
                    navController = navController,
                    watchability = route.watchability
                )
            }

            composable<GroupPersonRoute>(
                typeMap = mapOf(
                    typeOf<List<PersonMovieScreenObject>>() to PersonMovieListScreenObjectType
                )
            ) {
                val route = it.toRoute<GroupPersonRoute>()

                val component = DaggerMovieComponent.factory().create(dependency)
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
    }
}
