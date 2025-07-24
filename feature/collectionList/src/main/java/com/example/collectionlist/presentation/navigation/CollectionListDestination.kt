package com.example.collectionlist.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.collectionlist.di.DaggerCollectionComponent
import com.example.collectionlist.presentation.CollectionListScreen
import com.example.collectionlist.presentation.CollectionListViewModel
import com.example.navigationroute.CollectionListRoute
import dev.chrisbanes.haze.HazeState

fun NavGraphBuilder.collectionListDestination(
    navController: NavController,
    hazeState: HazeState
) {
    composable<CollectionListRoute> {
        val route = it.toRoute<CollectionListRoute>()
        val component = DaggerCollectionComponent
            .factory()
            .create()

        val viewModel: CollectionListViewModel = viewModel(
            factory = component.viewModelFactory
        )

        CollectionListScreen(
            navController = navController,
            viewModel = viewModel,
            hazeState = hazeState,
            category = route.category
        )
    }
}