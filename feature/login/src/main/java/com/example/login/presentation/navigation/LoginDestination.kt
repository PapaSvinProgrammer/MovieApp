package com.example.login.presentation.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.login.presentation.startScreen.StartScreen
import com.example.login.presentation.startScreen.StartViewModel
import com.example.login.startScreen.di.DaggerStartComponent
import com.example.navigationroute.StartRoute

fun NavGraphBuilder.loginDestination(navController: NavController) {
    composable<StartRoute> {
        val component = DaggerStartComponent
            .factory()
            .create(LocalContext.current)

        val viewModel: StartViewModel = viewModel(
            factory = component.viewModelFactory
        )

        StartScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}