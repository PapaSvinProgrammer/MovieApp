package com.mordva.collectionlist.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.collectionlist.di.CollectionDependency
import com.mordva.collectionlist.di.DaggerCollectionComponent
import com.mordva.collectionlist.presentation.CollectionListScreen
import com.mordva.collectionlist.presentation.CollectionListViewModel
import com.mordva.navigation.CollectionListGraph
import com.mordva.navigation.FeatureApi
import dev.chrisbanes.haze.HazeState

class CollectionListFeatureImpl(
    private val dependency: CollectionDependency
) : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        hazeState: HazeState,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<CollectionListGraph>(
            startDestination = CollectionListGraph.CollectionListRoute(null, listOf())
        ) {
            composable<CollectionListGraph.CollectionListRoute> {
                val route = it.toRoute<CollectionListGraph.CollectionListRoute>()

                val component = DaggerCollectionComponent.factory().create(dependency)
                val viewModel: CollectionListViewModel = viewModel(
                    factory = component.viewModelFactory
                )

                if (route.category != null) {
                    CollectionListScreen(
                        navController = navController,
                        viewModel = viewModel,
                        hazeState = hazeState,
                        category = route.category ?: ""
                    )
                } else if (route.listId.isNotEmpty()) {
                    CollectionListScreen(
                        navController = navController,
                        viewModel = viewModel,
                        hazeState = hazeState,
                        listId = route.listId
                    )
                } else {
                    CollectionListScreen(
                        navController = navController,
                        viewModel = viewModel,
                        hazeState = hazeState
                    )
                }
            }
        }
    }
}