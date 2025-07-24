package com.example.favorite

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigationroute.FavoriteRoute

fun NavGraphBuilder.favoriteDestination(navController: NavController) {
    composable<FavoriteRoute> {
        FavoriteScreen(
            navController = navController
        )
    }
}