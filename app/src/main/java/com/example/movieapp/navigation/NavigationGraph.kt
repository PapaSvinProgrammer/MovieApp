package com.example.movieapp.navigation

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
import com.example.aboutapp.AboutAppScreen
import com.example.home.presentation.HomeScreen
import com.example.home.presentation.HomeViewModel
import com.example.movieapp.di.viewModel.ViewModelFactory
import com.example.navigationroute.AboutAppRoute
import com.example.navigationroute.HomeRoute
import com.example.navigationroute.MovieRoute
import com.example.navigationroute.NavRoute
import com.example.navigationroute.SettingsRoute
import com.example.settings.SettingsScreen
import com.example.settings.SettingsViewModel
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
//        composable<com.example.navigationroute.StartRoute> {
//            val viewModel: StartViewModel = viewModel(factory = viewModelFactory)
//
//            StartScreen(
//                navController = navController,
//                viewModel = viewModel
//            )
//        }

        composable<HomeRoute> {
            val viewModel: HomeViewModel = viewModel(factory = viewModelFactory)

            HomeScreen(
                navController = navController,
                hazeState = hazeState,
                viewModel = viewModel
            )
        }

//        composable<com.example.navigationroute.SearchRoute> {
//            val viewModel: SearchViewModel = viewModel(factory = viewModelFactory)
//
//            SearchScreen(
//                navController = navController,
//                viewModel = viewModel,
//                hazeState = hazeState
//            )
//        }
//
//        composable<com.example.navigationroute.AccountRoute> {
//            AccountScreen(
//                navController = navController
//            )
//        }
//
//        composable<com.example.navigationroute.FavoriteRoute> {
//            FavoriteScreen(
//                navController = navController
//            )
//        }

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
//
//        composable<com.example.navigationroute.SearchSettingsRoute> {
//            val viewModel: SearchSettingsViewModel = viewModel(factory = viewModelFactory)
//
//            SearchSettingsScreen(
//                navController = navController,
//                viewModel = viewModel
//            )
//        }
//
//        composable<com.example.navigationroute.SearchResultRoute>(
//            typeMap = mapOf(
//                typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListType
//            )
//        ) {
//            val data = it.toRoute<com.example.navigationroute.SearchResultRoute>()
//            val viewModel: SearchResultViewModel = viewModel(factory = viewModelFactory)
//
//            SearchResultScreen(
//                navController = navController,
//                viewModel = viewModel,
//                queryParameters = data.queryParameters,
//                hazeState = hazeState
//            )
//        }
//
//        composable<com.example.navigationroute.CollectionListRoute> {
//            val route = it.toRoute<com.example.navigationroute.CollectionListRoute>()
//            val viewModel: CollectionListViewModel = viewModel(factory = viewModelFactory)
//
//            CollectionListScreen(
//                navController = navController,
//                viewModel = viewModel,
//                hazeState = hazeState,
//                category = route.category
//            )
//        }
//
//        composable<com.example.navigationroute.MovieListRoute>(
//            typeMap = mapOf(
//                typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListType
//            )
//        ) {
//            val route = it.toRoute<com.example.navigationroute.MovieListRoute>()
//            val viewModel: MovieListViewModel = viewModel(factory = viewModelFactory)
//
//            MovieListScreen(
//                navController = navController,
//                viewModel = viewModel,
//                hazeState = hazeState,
//                title = route.title,
//                queryParameters = route.queryParameters
//            )
//        }
//
//        composable<com.example.navigationroute.HomeDetailListRoute>(
//            typeMap = mapOf(
//                typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListType
//            )
//        ) {
//            val route = it.toRoute<com.example.navigationroute.HomeDetailListRoute>()
//            val viewModel: MovieListViewModel = viewModel(factory = viewModelFactory)
//
//            HomeDetailListScreen(
//                navController = navController,
//                viewModel = viewModel,
//                hazeState = hazeState,
//                title = route.title,
//                queryParameters = route.queryParameters
//            )
//        }
//
//        composable<com.example.navigationroute.PersonPodiumListRoute>(
//            typeMap = mapOf(
//                typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListType
//            )
//        ) {
//            val route = it.toRoute<com.example.navigationroute.PersonPodiumListRoute>()
//            val viewMode: PersonListViewModel = viewModel(factory = viewModelFactory)
//
//            PersonPodiumListScreen(
//                navController = navController,
//                viewModel = viewMode,
//                hazeState = hazeState,
//                title = route.title,
//                queryParameters = route.queryParameters
//            )
//        }
//
//        composable<com.example.navigationroute.PersonRoute> {
//            val route = it.toRoute<com.example.navigationroute.PersonRoute>()
//            val viewModel: PersonViewModel = viewModel(factory = viewModelFactory)
//
//            PersonScreen(
//                navController = navController,
//                viewModel = viewModel,
//                hazeState = hazeState,
//                id = route.id
//            )
//        }
//
//        composable<com.example.navigationroute.PersonDetailRoute> {
//            val route = it.toRoute<com.example.navigationroute.PersonDetailRoute>()
//            val viewModel: PersonViewModel = viewModel(factory = viewModelFactory)
//
//            PersonDetailScreen(
//                navController = navController,
//                viewModel = viewModel,
//                id = route.id
//            )
//        }
//
//        composable<com.example.navigationroute.AwardListRoute> {
//            val route = it.toRoute<com.example.navigationroute.AwardListRoute>()
//            val viewModel: AwardListViewModel = viewModel(factory = viewModelFactory)
//
//            AwardListScreen(
//                navController = navController,
//                viewModel = viewModel,
//                hazeState = hazeState,
//                id = route.id,
//                isMovie = route.isMovie
//            )
//        }
//
//        composable<com.example.navigationroute.MovieRoute> {
//            val route = it.toRoute<com.example.navigationroute.MovieRoute>()
//            val viewModel: MovieViewModel = viewModel(factory = viewModelFactory)
//
//            MovieScreen(
//                navController = navController,
//                viewModel = viewModel,
//                hazeState = hazeState,
//                id = route.id
//            )
//        }
    }
}