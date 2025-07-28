package com.example.favorite

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigationroute.MainRoutes

fun NavGraphBuilder.favoriteDestination(navController: NavController) {
    composable<MainRoutes.FavoriteRoute> {
        FavoriteScreen(
            navController = navController
        )
    }
}