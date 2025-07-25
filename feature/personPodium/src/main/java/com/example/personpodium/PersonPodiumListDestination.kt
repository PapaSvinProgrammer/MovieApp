package com.example.personpodium

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.corecomponent.AppComponent
import com.example.navigationroute.CustomNavType
import com.example.navigationroute.PersonPodiumListRoute
import com.example.personlistviewmodel.PersonListViewModel
import com.example.personlistviewmodel.di.DaggerPersonListComponent
import dev.chrisbanes.haze.HazeState
import kotlin.reflect.typeOf

fun NavGraphBuilder.personPodiumListDestination(
    appComponent: AppComponent,
    navController: NavController,
    hazeState: HazeState
) {
    composable<PersonPodiumListRoute>(
        typeMap = mapOf(
            typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListType
        )
    ) {
        val route = it.toRoute<PersonPodiumListRoute>()
        val component = DaggerPersonListComponent
            .factory()
            .create(appComponent)

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