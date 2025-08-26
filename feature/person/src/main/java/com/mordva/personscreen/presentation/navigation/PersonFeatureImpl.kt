package com.mordva.personscreen.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.PersonGraph
import com.mordva.personscreen.di.DaggerPersonComponent
import com.mordva.personscreen.di.PersonDependency
import com.mordva.personscreen.presentation.PersonDetailScreen
import com.mordva.personscreen.presentation.PersonScreen
import com.mordva.personscreen.presentation.PersonViewModel
import dev.chrisbanes.haze.HazeState

class PersonFeatureImpl(
    private val dependency: PersonDependency
) : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        hazeState: HazeState,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<PersonGraph>(
            startDestination = PersonGraph.PersonRoute(1)
        ) {
            composable<PersonGraph.PersonRoute> {
                val route = it.toRoute<PersonGraph.PersonRoute>()

                val component = DaggerPersonComponent.factory().create(dependency)
                val viewModel: PersonViewModel = viewModel(
                    factory = component.viewModelFactory
                )

                PersonScreen(
                    navController = navController,
                    viewModel = viewModel,
                    hazeState = hazeState,
                    id = route.id
                )
            }

            composable<PersonDetailRoute> {
                val route = it.toRoute<PersonDetailRoute>()

                val component = DaggerPersonComponent.factory().create(dependency)
                val viewModel: PersonViewModel = viewModel(
                    factory = component.viewModelFactory
                )

                PersonDetailScreen(
                    navController = navController,
                    viewModel = viewModel,
                    id = route.id
                )
            }
        }
    }
}