package com.example.movieapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.movieapp.app.navigation.BottomBarItems
import com.example.movieapp.app.navigation.NavRoute
import com.example.movieapp.app.navigation.NavigationGraph
import com.example.movieapp.app.navigation.SearchSettingsRoute
import com.example.movieapp.di.viewModel.ViewModelFactory
import com.example.movieapp.ui.widget.other.HazeBottomBar
import dev.chrisbanes.haze.rememberHazeState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    viewModelFactory: ViewModelFactory,
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
            viewModelFactory = viewModelFactory,
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