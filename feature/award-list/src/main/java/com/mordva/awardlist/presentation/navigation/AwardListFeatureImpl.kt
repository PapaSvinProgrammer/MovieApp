package com.mordva.awardlist.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.awardlist.di.AwardListDependency
import com.mordva.awardlist.di.DaggerAwardListComponent
import com.mordva.awardlist.presentation.AwardListScreen
import com.mordva.awardlist.presentation.AwardListViewModel
import com.mordva.navigation.AwardListGraph
import com.mordva.navigation.FeatureApi
import dev.chrisbanes.haze.HazeState

class AwardListFeatureImpl(
    private val dependency: AwardListDependency
) : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        hazeState: HazeState,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<AwardListGraph>(
            startDestination = AwardListGraph.AwardsListRoute(1, false)
        ) {
            composable<AwardListGraph.AwardsListRoute> {
                val route = it.toRoute<AwardListGraph.AwardsListRoute>()

                val component = DaggerAwardListComponent.factory().create(dependency)
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
    }
}