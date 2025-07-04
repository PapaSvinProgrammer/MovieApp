package com.example.movieapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.aboutapp.AboutAppScreen
import com.example.account.AccountScreen
import com.example.awardlist.AwardListScreen
import com.example.awardlist.di.DaggerAwardListComponent
import com.example.collectionlist.CollectionListScreen
import com.example.collectionlist.di.DaggerCollectionComponent
import com.example.favorite.FavoriteScreen
import com.example.home.di.DaggerHomeComponent
import com.example.home.presentation.HomeDetailListScreen
import com.example.home.presentation.HomeScreen
import com.example.home.presentation.HomeViewModel
import com.example.login.startScreen.StartScreen
import com.example.movieScreen.MovieScreen
import com.example.movieScreen.di.DaggerMovieComponent
import com.example.movielist.MovieListScreen
import com.example.movielistviewmodel.di.DaggerMovieListComponent
import com.example.viewmodelfactory.ViewModelFactory
import com.example.navigationroute.AboutAppRoute
import com.example.navigationroute.AccountRoute
import com.example.navigationroute.AwardListRoute
import com.example.navigationroute.CollectionListRoute
import com.example.navigationroute.FavoriteRoute
import com.example.navigationroute.HomeDetailListRoute
import com.example.navigationroute.HomeRoute
import com.example.navigationroute.MovieListRoute
import com.example.navigationroute.MovieRoute
import com.example.navigationroute.NavRoute
import com.example.navigationroute.SettingsRoute
import com.example.navigationroute.StartRoute
import com.example.personpodium.PersonPodiumListScreen
import com.example.settings.SettingsScreen
import com.example.settings.SettingsViewModel
import com.example.settings.di.DaggerSettingsComponent
import dev.chrisbanes.haze.HazeState
import kotlin.reflect.typeOf

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    hazeState: HazeState,
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
//        composable<StartRoute> {
//            val viewModel: StartViewModel = viewModel(factory = viewModelFactory)
//
//            StartScreen(
//                navController = navController,
//                viewModel = viewModel
//            )
//        }

        composable<HomeRoute> {
            val component = DaggerHomeComponent.factory().create()

            HomeScreen(
                navController = navController,
                hazeState = hazeState,
                viewModel = remember { component.viewModel }
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
            val component = DaggerSettingsComponent.factory().create(LocalContext.current)

            SettingsScreen(
                navController = navController,
                viewModel = remember { component.viewModel }
            )
        }

        composable<AboutAppRoute> {
            AboutAppScreen(
                navController = navController
            )
        }

//        composable<com.example.navigationroute.SearchSettingsRoute> {
//            val viewModel: SearchSettingsViewModel = viewModel(factory = viewModelFactory)
//
//            SearchSettingsScreen(
//                navController = navController,
//                viewModel = viewModel
//            )
//        }

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

        composable<CollectionListRoute> {
            val route = it.toRoute<CollectionListRoute>()
            val component = DaggerCollectionComponent.factory().create()

            CollectionListScreen(
                navController = navController,
                viewModel = remember { component.viewModel },
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
            val component = DaggerMovieListComponent.factory().create()

            MovieListScreen(
                navController = navController,
                viewModel = remember { component.viewModel },
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
            val component = DaggerMovieListComponent.factory().create()

            HomeDetailListScreen(
                navController = navController,
                viewModel = remember { component.viewModel },
                hazeState = hazeState,
                title = route.title,
                queryParameters = route.queryParameters
            )
        }

//        composable<com.example.navigationroute.PersonPodiumListRoute>(
//            typeMap = mapOf(
//                typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListType
//            )
//        ) {
//            val route = it.toRoute<com.example.navigationroute.PersonPodiumListRoute>()
//
//            PersonPodiumListScreen(
//                navController = navController,
//                viewModel = viewMode,
//                hazeState = hazeState,
//                title = route.title,
//                queryParameters = route.queryParameters
//            )
//        }

//        composable<com.example.navigationroute.PersonRoute> {
//            val route = it.toRoute<com.example.navigationroute.PersonRoute>()
//            val viewModel: PersonViewModel = viewModel(factory = viewModelFactory)
//            Dagger
//            PersonScreen(
//                navController = navController,
//                viewModel = viewModel,
//                hazeState = hazeState,
//                id = route.id
//            )
//        }

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

        composable<AwardListRoute> {
            val route = it.toRoute<AwardListRoute>()
            val component = DaggerAwardListComponent.factory().create()

            AwardListScreen(
                navController = navController,
                viewModel = remember { component.viewModel },
                hazeState = hazeState,
                id = route.id,
                isMovie = route.isMovie
            )
        }

        composable<MovieRoute> {
            val route = it.toRoute<MovieRoute>()
            val component = DaggerMovieComponent.factory().create()

            MovieScreen(
                navController = navController,
                viewModel = remember { component.viewModel },
                hazeState = hazeState,
                id = route.id
            )
        }
    }
}