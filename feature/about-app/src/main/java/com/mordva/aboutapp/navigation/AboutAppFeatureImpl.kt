package com.mordva.aboutapp.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mordva.aboutapp.AboutAppScreen
import com.mordva.navigation.AboutAppGraph
import com.mordva.navigation.FeatureApi
import dev.chrisbanes.haze.HazeState

class AboutAppFeatureImpl : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        hazeState: HazeState,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<AboutAppGraph>(
            startDestination = AboutAppRoute
        ) {
            composable<AboutAppRoute> {
                AboutAppScreen(
                    navController = navController
                )
            }
        }
    }
}