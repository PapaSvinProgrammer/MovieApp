package com.example.settings.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigationroute.SettingsRoutes
import com.example.settings.di.DaggerSettingsComponent
import com.example.settings.di.SettingsDependency
import com.example.settings.presentation.confidential.ConfidentialScreen
import com.example.settings.presentation.confidential.ConfidentialViewModel
import com.example.settings.presentation.decor.DecorScreen
import com.example.settings.presentation.decor.DecorViewModel
import com.example.settings.presentation.sound.SoundScreen
import com.example.settings.presentation.sound.SoundViewModel

fun NavGraphBuilder.settingsDestination(
    dependency: SettingsDependency,
    navController: NavController
) {
    composable<SettingsRoutes.DecorRoute> {
        val component = DaggerSettingsComponent
            .factory()
            .create(dependency)

        val viewModel: DecorViewModel = viewModel(
            factory = component.viewModelFactory
        )

        DecorScreen(
            navController = navController,
            viewModel = viewModel
        )
    }

    composable<SettingsRoutes.SoundRoute> {
        val component = DaggerSettingsComponent
            .factory()
            .create(dependency)

        val viewModel: SoundViewModel = viewModel(
            factory = component.viewModelFactory
        )

        SoundScreen(
            navController = navController,
            viewModel = viewModel
        )
    }

    composable<SettingsRoutes.ConfidentialRoute> {
        val component = DaggerSettingsComponent
            .factory()
            .create(dependency)

        val viewModel: ConfidentialViewModel = viewModel(
            factory = component.viewModelFactory
        )

        ConfidentialScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}
