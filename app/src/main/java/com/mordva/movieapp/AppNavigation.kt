package com.mordva.movieapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.MovieGraph
import dev.chrisbanes.haze.HazeState

@Composable
fun AppNavigation(
    startDestination: Boolean,
    navController: NavHostController,
    hazeState: HazeState,
    modifier: Modifier = Modifier,
    list: List<FeatureApi>
) {
    NavHost(
        navController = navController,
        startDestination = MovieGraph,
        modifier = modifier
    ) {
        list.forEach {
            it.registerGraph(this, navController, hazeState)
        }
    }
}