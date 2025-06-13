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
import com.example.movieapp.ui.screen.AwardListScreen
import com.example.movieapp.ui.screen.CollectionListScreen
import com.example.movieapp.ui.screen.FavoriteScreen
import com.example.movieapp.ui.screen.HomeDetailListScreen
import com.example.movieapp.ui.screen.HomeScreen
import com.example.movieapp.ui.screen.MovieListScreen
import com.example.movieapp.ui.screen.MovieScreen
import com.example.movieapp.ui.screen.PersonDetailScreen
import com.example.movieapp.ui.screen.PersonPodiumListScreen
import com.example.movieapp.ui.screen.PersonScreen
import com.example.movieapp.ui.screen.SearchResultScreen
import com.example.movieapp.ui.screen.SearchScreen
import com.example.movieapp.ui.screen.SearchSettingsScreen
import com.example.movieapp.ui.screen.SettingsScreen
import com.example.movieapp.ui.screen.StartScreen
import com.example.movieapp.viewModels.AwardListViewModel
import com.example.movieapp.viewModels.PersonListViewModel
import com.example.movieapp.viewModels.CollectionListViewModel
import com.example.movieapp.viewModels.HomeViewModel
import com.example.movieapp.viewModels.MovieListViewModel
import com.example.movieapp.viewModels.MovieViewModel
import com.example.movieapp.viewModels.PersonViewModel
import com.example.movieapp.viewModels.SearchResultViewModel
import com.example.movieapp.viewModels.SearchSettingsViewModel
import com.example.movieapp.viewModels.SearchViewModel
import com.example.movieapp.viewModels.SettingsViewModel
import com.example.movieapp.viewModels.StartViewModel
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
                typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListType
            )
        ) {
            val data = it.toRoute<SearchResultRoute>()
            val viewModel: SearchResultViewModel = viewModel(factory = viewModelFactory)

            SearchResultScreen(
                navController = navController,
                viewModel = viewModel,
                queryParameters = data.queryParameters,
                hazeState = hazeState
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
                typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListType
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

        composable<HomeDetailListRoute>(
            typeMap = mapOf(
                typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListType
            )
        ) {
            val route = it.toRoute<HomeDetailListRoute>()
            val viewModel: MovieListViewModel = viewModel(factory = viewModelFactory)

            HomeDetailListScreen(
                navController = navController,
                viewModel = viewModel,
                hazeState = hazeState,
                title = route.title,
                queryParameters = route.queryParameters
            )
        }

        composable<PersonPodiumListRoute>(
            typeMap = mapOf(
                typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListType
            )
        ) {
            val route = it.toRoute<PersonPodiumListRoute>()
            val viewMode: PersonListViewModel = viewModel(factory = viewModelFactory)

            PersonPodiumListScreen(
                navController = navController,
                viewModel = viewMode,
                hazeState = hazeState,
                title = route.title,
                queryParameters = route.queryParameters
            )
        }

        composable<PersonRoute> {
            val route = it.toRoute<PersonRoute>()
            val viewModel: PersonViewModel = viewModel(factory = viewModelFactory)

            PersonScreen(
                navController = navController,
                viewModel = viewModel,
                hazeState = hazeState,
                id = route.id
            )
        }

        composable<PersonDetailRoute> {
            val route = it.toRoute<PersonDetailRoute>()
            val viewModel: PersonViewModel = viewModel(factory = viewModelFactory)

            PersonDetailScreen(
                navController = navController,
                viewModel = viewModel,
                id = route.id
            )
        }

        composable<AwardListRoute> {
            val route = it.toRoute<AwardListRoute>()
            val viewModel: AwardListViewModel = viewModel(factory = viewModelFactory)

            AwardListScreen(
                navController = navController,
                viewModel = viewModel,
                hazeState = hazeState,
                id = route.id,
                isMovie = route.isMovie
            )
        }

        composable<MovieRoute> {
            val route = it.toRoute<MovieRoute>()
            val viewModel: MovieViewModel = viewModel(factory = viewModelFactory)

            MovieScreen(
                navController = navController,
                viewModel = viewModel,
                hazeState = hazeState,
                id = route.id
            )
        }
    }
}