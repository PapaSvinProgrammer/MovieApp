package com.mordva.settings.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.SettingsGraph
import com.mordva.settings.di.DaggerSettingsComponent
import com.mordva.settings.di.SettingsDependency
import com.mordva.settings.presentation.confidential.ConfidentialScreen
import com.mordva.settings.presentation.confidential.ConfidentialViewModel
import com.mordva.settings.presentation.decor.DecorScreen
import com.mordva.settings.presentation.decor.DecorViewModel
import com.mordva.settings.presentation.sound.SoundScreen
import com.mordva.settings.presentation.sound.SoundViewModel
import dev.chrisbanes.haze.HazeState

class SettingsFeatureImpl(
    private val dependency: SettingsDependency
) : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        hazeState: HazeState,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<SettingsGraph>(
            startDestination = SettingsGraph.DecorRoute
        ) {
            composable<SettingsGraph.DecorRoute> {
                val component = DaggerSettingsComponent.factory().create(dependency)
                val viewModel: DecorViewModel = viewModel(
                    factory = component.viewModelFactory
                )

                DecorScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable<SettingsGraph.SoundRoute> {
                val component = DaggerSettingsComponent.factory().create(dependency)
                val viewModel: SoundViewModel = viewModel(
                    factory = component.viewModelFactory
                )

                SoundScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable<SettingsGraph.ConfidentialRoute> {
                val component = DaggerSettingsComponent.factory().create(dependency)
                val viewModel: ConfidentialViewModel = viewModel(
                    factory = component.viewModelFactory
                )

                ConfidentialScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}