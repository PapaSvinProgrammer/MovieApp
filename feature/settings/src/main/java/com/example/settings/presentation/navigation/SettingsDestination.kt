package com.example.settings.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.corecomponent.AppComponent
import com.example.navigationroute.SettingsRoute
import com.example.settings.di.DaggerSettingsComponent
import com.example.settings.presentation.SettingsScreen
import com.example.settings.presentation.SettingsViewModel

fun NavGraphBuilder.settingsDestination(
    appComponent: AppComponent,
    navController: NavController
) {
    composable<SettingsRoute> {
        val component = DaggerSettingsComponent
            .factory()
            .create(appComponent)

        val viewModel: SettingsViewModel = viewModel(
            factory = component.viewModelFactory
        )

        SettingsScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}
