package com.mordva.login.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mordva.login.di.DaggerLoginComponent
import com.mordva.login.di.LoginDependency
import com.mordva.login.presentation.LoginScreen
import com.mordva.login.presentation.LoginViewModel
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.LoginGraph
import dev.chrisbanes.haze.HazeState

class LoginFeatureImpl(
    private val dependency: LoginDependency
) : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        hazeState: HazeState,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<LoginGraph>(
            startDestination = LoginRoute
        ) {
            composable<LoginRoute> {
                val component = DaggerLoginComponent.factory().create(dependency)
                val viewModel: LoginViewModel = viewModel(
                    factory = component.viewModelFactory
                )

                LoginScreen(
                    navController = navController,
                    viewModel = viewModel,
                    hazeState = hazeState
                )
            }
        }
    }
}