package com.example.account

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigationroute.MainRoutes
import dev.chrisbanes.haze.HazeState

fun NavGraphBuilder.accountDestination(
    navController: NavController,
    hazeState: HazeState
) {
    composable<MainRoutes.AccountRoute> {
        AccountScreen(
            navController = navController,
            hazeState = hazeState
        )
    }
}