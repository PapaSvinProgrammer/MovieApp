package com.example.movieapp.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.di.viewModel.ViewModelFactory
import com.example.movieapp.presentation.screen.AccountScreen
import com.example.movieapp.presentation.screen.FavoriteScreen
import com.example.movieapp.presentation.screen.HomeScreen
import com.example.movieapp.presentation.screen.SearchScreen
import com.example.movieapp.presentation.screen.StartScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    mainPadding: PaddingValues,
    viewModelFactory: ViewModelFactory,
    startRoute: NavRoute
) {
    //TODO

    NavHost(
        navController = navController,
        startDestination = HomeRoute,
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
            StartScreen(
                navController = navController,
                mainPadding = mainPadding
            )
        }

        composable<HomeRoute> {
            HomeScreen(
                navController = navController,
                mainPadding = mainPadding
            )
        }

        composable<SearchRoute> {
            SearchScreen(
                navController = navController,
                mainPadding = mainPadding
            )
        }

        composable<AccountRoute> {
            AccountScreen(
                navController = navController,
                mainPadding = mainPadding
            )
        }

        composable<FavoriteRoute> {
            FavoriteScreen(
                navController = navController,
                mainPadding = mainPadding
            )
        }
    }
}