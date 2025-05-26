package com.example.movieapp.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.app.navigation.AccountRoute
import com.example.movieapp.app.navigation.BottomBarItems
import com.example.movieapp.app.navigation.FavoriteRoute
import com.example.movieapp.app.navigation.HomeRoute
import com.example.movieapp.app.navigation.NavRoute
import com.example.movieapp.app.navigation.NavigationGraph
import com.example.movieapp.app.navigation.SearchRoute
import com.example.movieapp.di.viewModel.ViewModelFactory

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

    Scaffold(
        bottomBar = {
            NavigationBottomBar(
                navController = navController,
                visibility = bottomBarVisible
            )
        }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            mainPadding = innerPadding,
            viewModelFactory = viewModelFactory,
            startRoute = startRoute
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

@Composable
private fun NavigationBottomBar(
    navController: NavHostController,
    visibility: Boolean
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    AnimatedVisibility(
        visible = visibility,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        NavigationBar {
            BottomBarItems.items.forEach {
                val isSelected = currentRoute == it.route::class.java.canonicalName

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(it.route) {
                            popUpTo(
                                id = navController.graph.findStartDestination().id
                            ) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        val icon = if (isSelected) it.iconFill else it.icon

                        Icon(
                            painter = painterResource(icon),
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}