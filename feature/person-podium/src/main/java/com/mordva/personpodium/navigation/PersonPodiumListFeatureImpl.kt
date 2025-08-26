package com.mordva.personpodium.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.base_view_models.person_list.DaggerPersonListComponent
import com.mordva.base_view_models.person_list.PersonListDependency
import com.mordva.base_view_models.person_list.PersonListViewModel
import com.mordva.navigation.CustomNavType
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.PersonPodiumListGraph
import com.mordva.personpodium.PersonPodiumListScreen
import dev.chrisbanes.haze.HazeState
import kotlin.reflect.typeOf

class PersonPodiumListFeatureImpl(
    private val dependency: PersonListDependency
) : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        hazeState: HazeState,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<PersonPodiumListGraph>(
            startDestination = PersonPodiumListGraph.PersonPodiumListRoute("", listOf())
        ) {
            composable<PersonPodiumListGraph.PersonPodiumListRoute>(
                typeMap = mapOf(
                    typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListTypePair
                )
            ) {
                val route = it.toRoute<PersonPodiumListGraph.PersonPodiumListRoute>()

                val component = DaggerPersonListComponent.factory().create(dependency)
                val viewModel: PersonListViewModel = viewModel(
                    factory = component.viewModelFactory
                )

                PersonPodiumListScreen(
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