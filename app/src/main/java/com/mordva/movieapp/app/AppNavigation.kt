package com.mordva.movieapp.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.ImageListGraph
import com.mordva.navigation.MovieGraph
import com.mordva.navigation.RootGraph
import dev.chrisbanes.haze.HazeState

@Composable
fun AppNavigation(
    startRoute: RootGraph,
    navController: NavHostController,
    hazeState: HazeState,
    modifier: Modifier = Modifier,
    list: List<FeatureApi>
) {
    NavHost(
        navController = navController,
        startDestination = ImageListGraph,
        modifier = modifier
    ) {
        list.forEach {
            it.registerGraph(this, navController, hazeState)
        }
    }
}