package com.example.aboutapp

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigationroute.SettingsRoutes

fun NavGraphBuilder.aboutAppDestination(navController: NavController) {
    composable< SettingsRoutes.AboutAppRoute> {
        AboutAppScreen(
            navController = navController
        )
    }
}