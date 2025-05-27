package com.example.movieapp.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.app.navigation.AccountRoute
import com.example.movieapp.app.navigation.BottomBarItems
import com.example.movieapp.app.navigation.BottomBarTab
import com.example.movieapp.app.navigation.FavoriteRoute
import com.example.movieapp.app.navigation.HomeRoute
import com.example.movieapp.app.navigation.NavRoute
import com.example.movieapp.app.navigation.NavigationGraph
import com.example.movieapp.app.navigation.SearchRoute
import com.example.movieapp.di.viewModel.ViewModelFactory
import com.example.movieapp.presentation.widget.HazeBottomBar
import dev.chrisbanes.haze.HazeState

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

    var selectedTab by remember {
        mutableIntStateOf(
            BottomBarItems.items.indexOf(BottomBarTab.Home)
        )
    }
    val hazeState = remember { HazeState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            HazeBottomBar(
                tabs = BottomBarItems.items,
                hazeState = hazeState,
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = BottomBarItems.items.indexOf(it)
                    navController.navigate(it.route)
                }
            )
        }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            mainPadding = innerPadding,
            viewModelFactory = viewModelFactory,
            startRoute = startRoute,
            hazeState = hazeState
        )
    }
}

private fun bottomBarIsVisibility(route: String?, onResult: (Boolean) -> Unit) {
    when (route) {
        AccountRoute::class.java.canonicalName -> onResult(true)
        HomeRoute::class.java.canonicalName  -> onResult(true)
        SearchRoute::class.java.canonicalName  -> onResult(true)
        FavoriteRoute::class.java.canonicalName -> onResult(true)
        else -> onResult(false)
    }
}