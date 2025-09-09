package com.mordva.images_list.presentation.navigation

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.images_list.di.DaggerImageListComponent
import com.mordva.images_list.di.ImageListDependency
import com.mordva.images_list.presentation.ImageListScreen
import com.mordva.images_list.presentation.ImageListViewModel
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.ImageListGraph
import dev.chrisbanes.haze.HazeState

class ImageListFeatureImpl(private val dependency: ImageListDependency) : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        hazeState: HazeState,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<ImageListGraph>(
            startDestination = ImageListGraph.ImageListRoute(326)
        ) {
            composable<ImageListGraph.ImageListRoute> {
                val route = it.toRoute<ImageListGraph.ImageListRoute>()
                val component = remember { DaggerImageListComponent.factory().create(dependency) }
                val viewModel: ImageListViewModel = viewModel(
                    factory = component.viewModelFactory
                )

                ImageListScreen(
                    navController = navController,
                    viewModel = viewModel,
                    hazeState = hazeState,
                    movieId = route.movieId
                )
            }
        }
    }
}