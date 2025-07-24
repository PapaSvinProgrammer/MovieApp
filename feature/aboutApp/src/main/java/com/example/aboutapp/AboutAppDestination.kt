package com.example.aboutapp

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigationroute.AboutAppRoute

fun NavGraphBuilder.aboutAppDestination(navController: NavController) {
    composable<AboutAppRoute> {
        AboutAppScreen(
            navController = navController
        )
    }
}