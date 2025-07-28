package com.example.personscreen.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.corecomponent.AppComponent
import com.example.navigationroute.PersonDetailRoute
import com.example.navigationroute.PersonRoute
import com.example.personscreen.di.DaggerPersonComponent
import com.example.personscreen.presentation.PersonDetailScreen
import com.example.personscreen.presentation.PersonScreen
import com.example.personscreen.presentation.PersonViewModel
import dev.chrisbanes.haze.HazeState

fun NavGraphBuilder.personDestination(
    appComponent: AppComponent,
    navController: NavController,
    hazeState: HazeState
) {
    composable<PersonRoute> {
        val route = it.toRoute<PersonRoute>()
        val component = DaggerPersonComponent.factory().create(appComponent)

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
        val component = DaggerPersonComponent.factory().create(appComponent)

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
