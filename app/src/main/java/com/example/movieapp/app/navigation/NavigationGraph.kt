package com.example.movieapp.app.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.movieapp.app.utils.CustomNavType
import com.example.movieapp.di.viewModel.ViewModelFactory
import com.example.movieapp.ui.screen.AboutAppScreen
import com.example.movieapp.ui.screen.AccountScreen
import com.example.movieapp.ui.screen.CollectionListScreen
import com.example.movieapp.ui.screen.FavoriteScreen
import com.example.movieapp.ui.screen.HomeScreen
import com.example.movieapp.ui.screen.MovieListScreen
import com.example.movieapp.ui.screen.SearchResultScreen
import com.example.movieapp.ui.screen.SearchScreen
import com.example.movieapp.ui.screen.SearchSettingsScreen
import com.example.movieapp.ui.screen.SettingsScreen
import com.example.movieapp.ui.screen.StartScreen
import com.example.movieapp.ui.viewModel.CollectionListViewModel
import com.example.movieapp.ui.viewModel.HomeViewModel
import com.example.movieapp.ui.viewModel.MovieListViewModel
import com.example.movieapp.ui.viewModel.SearchResultViewModel
import com.example.movieapp.ui.viewModel.SearchSettingsViewModel
import com.example.movieapp.ui.viewModel.SearchViewModel
import com.example.movieapp.ui.viewModel.SettingsViewModel
import com.example.movieapp.ui.viewModel.StartViewModel
import dev.chrisbanes.haze.HazeState
import kotlin.reflect.typeOf

@RequiresApi(Build.VERSION_CODES.O)
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
                viewModel = viewModel
            )
        }

        composable<SearchResultRoute>(
            typeMap = mapOf(
                typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ArrayListType
            )
        ) {
            val data = it.toRoute<SearchResultRoute>()
            val viewModel: SearchResultViewModel = viewModel(factory = viewModelFactory)

            SearchResultScreen(
                navController = navController,
                viewModel = viewModel,
                queryParameters = data.queryParameters
            )
        }

        composable<CollectionListRoute> {
            val route = it.toRoute<CollectionListRoute>()
            val viewModel: CollectionListViewModel = viewModel(factory = viewModelFactory)

            CollectionListScreen(
                navController = navController,
                viewModel = viewModel,
                hazeState = hazeState,
                category = route.category
            )
        }

        composable<MovieListRoute>(
            typeMap = mapOf(
                typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ArrayListType
            )
        ) {
            val route = it.toRoute<MovieListRoute>()
            val viewModel: MovieListViewModel = viewModel(factory = viewModelFactory)

            MovieListScreen(
                navController = navController,
                viewModel = viewModel,
                hazeState = hazeState,
                title = route.title,
                queryParameters = route.queryParameters
            )
        }
    }
}