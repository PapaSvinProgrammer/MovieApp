package com.example.settings.presentation.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigationroute.SettingsRoute
import com.example.settings.presentation.SettingsScreen
import com.example.settings.presentation.SettingsViewModel
import com.example.settings.di.DaggerSettingsComponent

fun NavGraphBuilder.settingsDestination(navController: NavController) {
    composable<SettingsRoute> {
        val component = DaggerSettingsComponent
            .factory()
            .create(LocalContext.current)

        val viewModel: SettingsViewModel = viewModel(
            factory = component.viewModelFactory
        )

        SettingsScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}
