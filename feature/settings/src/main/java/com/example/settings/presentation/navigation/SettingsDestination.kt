package com.example.settings.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.corecomponent.AppComponent
import com.example.navigationroute.SettingsRoutes
import com.example.settings.di.DaggerSettingsComponent
import com.example.settings.presentation.SettingsScreen
import com.example.settings.presentation.SettingsViewModel
import com.example.settings.presentation.decor.DecorScreen

fun NavGraphBuilder.settingsDestination(
    appComponent: AppComponent,
    navController: NavController
) {
    composable<SettingsRoutes.DecorRoute> {
        val component = DaggerSettingsComponent
            .factory()
            .create(appComponent)

        val viewModel: SettingsViewModel = viewModel(
            factory = component.viewModelFactory
        )

        DecorScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}
