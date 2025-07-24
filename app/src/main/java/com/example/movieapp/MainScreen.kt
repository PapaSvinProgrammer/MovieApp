package com.example.movieapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigation.NavigationGraph
import com.example.navigationroute.NavRoute
import com.example.navigationroute.SearchSettingsRoute
import com.example.ui.widget.navigation.BottomBarItems
import com.example.ui.widget.navigation.HazeBottomBar
import dev.chrisbanes.haze.rememberHazeState

@Composable
fun MainScreen(
    startRoute: NavRoute
) {
    var bottomBarVisible by remember { mutableStateOf(false) }
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    bottomBarIsVisibility(
        route = currentRoute,
        onResult = { bottomBarVisible = it }
    )

    val hazeState = rememberHazeState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            HazeBottomBar(
                tabs = BottomBarItems.items,
                hazeState = hazeState,
                navController = navController,
                visible = bottomBarVisible
            )
        }
    ) { _ ->
        NavigationGraph(
            navController = navController,
            startRoute = startRoute,
            hazeState = hazeState
        )
    }
}

private fun bottomBarIsVisibility(route: String?, onResult: (Boolean) -> Unit) {
    when (route) {
        SearchSettingsRoute::class.java.canonicalName -> onResult(false)
        else -> onResult(true)
    }
}