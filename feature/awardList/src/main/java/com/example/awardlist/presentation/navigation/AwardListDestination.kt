package com.example.awardlist.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.awardlist.di.DaggerAwardListComponent
import com.example.awardlist.presentation.AwardListScreen
import com.example.awardlist.presentation.AwardListViewModel
import com.example.navigationroute.AwardListRoute
import dev.chrisbanes.haze.HazeState

fun NavGraphBuilder.awardListDestination(
    navController: NavController,
    hazeState: HazeState
) {
    composable<AwardListRoute> {
        val route = it.toRoute<AwardListRoute>()
        val component = DaggerAwardListComponent.factory().create()

        val viewModel: AwardListViewModel = viewModel(
            factory = component.viewModelFactory
        )

        AwardListScreen(
            navController = navController,
            viewModel = viewModel,
            hazeState = hazeState,
            id = route.id,
            isMovie = route.isMovie
        )
    }
}