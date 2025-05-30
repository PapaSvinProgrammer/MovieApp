package com.example.movieapp.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.di.viewModel.ViewModelFactory
import com.example.movieapp.ui.screen.AboutAppScreen
import com.example.movieapp.ui.screen.AccountScreen
import com.example.movieapp.ui.screen.FavoriteScreen
import com.example.movieapp.ui.screen.HomeScreen
import com.example.movieapp.ui.screen.SearchScreen
import com.example.movieapp.ui.screen.SearchSettingsScreen
import com.example.movieapp.ui.screen.SettingsScreen
import com.example.movieapp.ui.screen.StartScreen
import com.example.movieapp.ui.viewModel.HomeViewModel
import com.example.movieapp.ui.viewModel.SearchSettingsViewModel
import com.example.movieapp.ui.viewModel.SearchViewModel
import com.example.movieapp.ui.viewModel.SettingsViewModel
import com.example.movieapp.ui.viewModel.StartViewModel
import dev.chrisbanes.haze.HazeState

@Composable
fun NavigationGraph(
    navController: NavHostController,
    hazeState: HazeState,
    viewModelFactory: ViewModelFactory,
    startRoute: NavRoute
) {
    NavHost(
        navController = navController,
        startDestination = SearchRoute,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(300)
            )
        }
    ) {
        composable<StartRoute> {
            val viewModel: StartViewModel = viewModel(factory = viewModelFactory)

            StartScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<HomeRoute> {
            val viewModel: HomeViewModel = viewModel(factory = viewModelFactory)

            HomeScreen(
                navController = navController,
                hazeState = hazeState,
                viewModel = viewModel
            )
        }

        composable<SearchRoute> {
            val viewModel: SearchViewModel = viewModel(factory = viewModelFactory)

            SearchScreen(
                navController = navController,
                viewModel = viewModel,
                hazeState = hazeState
            )
        }

        composable<AccountRoute> {
            AccountScreen(
                navController = navController
            )
        }

        composable<FavoriteRoute> {
            FavoriteScreen(
                navController = navController
            )
        }

        composable<SettingsRoute> {
            val viewModel: SettingsViewModel = viewModel(factory = viewModelFactory)

            SettingsScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<AboutAppRoute> {
            AboutAppScreen(
                navController = navController
            )
        }

        composable<SearchSettingsRoute> {
            val viewModel: SearchSettingsViewModel = viewModel(factory = viewModelFactory)

            SearchSettingsScreen(
                navController = navController,
                hazeState = hazeState,
                viewModel = viewModel
            )
        }
    }
}