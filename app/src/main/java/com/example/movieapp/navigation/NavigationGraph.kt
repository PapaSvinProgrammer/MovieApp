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
import com.example.awardlist.AwardListViewModel
import com.example.awardlist.di.DaggerAwardListComponent
import com.example.collectionlist.CollectionListScreen
import com.example.collectionlist.CollectionListViewModel
import com.example.collectionlist.di.DaggerCollectionComponent
import com.example.favorite.FavoriteScreen
import com.example.home.di.DaggerHomeComponent
import com.example.home.presentation.HomeDetailListScreen
import com.example.home.presentation.HomeScreen
import com.example.home.presentation.HomeViewModel
import com.example.login.startScreen.StartScreen
import com.example.login.startScreen.StartViewModel
import com.example.login.startScreen.di.DaggerStartComponent
import com.example.movieScreen.MovieScreen
import com.example.movieScreen.MovieViewModel
import com.example.movieScreen.di.DaggerMovieComponent
import com.example.movielist.MovieListScreen
import com.example.movielistviewmodel.MovieListViewModel
import com.example.movielistviewmodel.di.DaggerMovieListComponent
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
import com.example.navigationroute.PersonDetailRoute
import com.example.navigationroute.PersonPodiumListRoute
import com.example.navigationroute.PersonRoute
import com.example.navigationroute.SearchResultRoute
import com.example.navigationroute.SearchRoute
import com.example.navigationroute.SearchSettingsRoute
import com.example.navigationroute.SettingsRoute
import com.example.navigationroute.StartRoute
import com.example.personlistviewmodel.PersonListViewModel
import com.example.personlistviewmodel.di.DaggerPersonListComponent
import com.example.personpodium.PersonPodiumListScreen
import com.example.personscreen.PersonDetailScreen
import com.example.personscreen.PersonScreen
import com.example.personscreen.PersonViewModel
import com.example.personscreen.di.DaggerPersonComponent
import com.example.search.searchResult.SearchResultScreen
import com.example.search.searchResult.SearchResultViewModel
import com.example.search.searchResult.di.DaggerSearchResultComponent
import com.example.search.searchScreen.SearchScreen
import com.example.search.searchScreen.SearchViewModel
import com.example.search.searchScreen.di.DaggerSearchComponent
import com.example.search.searchSettings.SearchSettingsScreen
import com.example.search.searchSettings.SearchSettingsViewModel
import com.example.search.searchSettings.di.DaggerSearchSettingsComponent
import com.example.settings.SettingsScreen
import com.example.settings.SettingsViewModel
import com.example.settings.di.DaggerSettingsComponent
import com.example.viewmodelfactory.ViewModelFactory
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
        startDestination = MovieRoute(8224),
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
            val component = DaggerStartComponent
                .factory()
                .create(LocalContext.current)

            val viewModel: StartViewModel = viewModel(
                factory = component.viewModelFactory
            )

            StartScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<HomeRoute> {
            val component = DaggerHomeComponent
                .factory()
                .create()

            val viewModel: HomeViewModel = viewModel(
                factory = component.viewModelFactory
            )

            HomeScreen(
                navController = navController,
                hazeState = hazeState,
                viewModel = viewModel
            )
        }

        composable<SearchRoute> {
            val component = DaggerSearchComponent
                .factory()
                .create(LocalContext.current)

            val viewModel: SearchViewModel = viewModel(
                factory = component.viewModelFactory
            )

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
            val component = DaggerSettingsComponent
                .factory()
                .create(LocalContext.current)

            val viewModel: SettingsViewModel = viewModel(
                factory = component.viewModelFactory
            )

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
            val component = DaggerSearchSettingsComponent
                .factory()
                .create()

            val viewModel: SearchSettingsViewModel = viewModel(
                factory = component.viewModelFactory
            )

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
            val component = DaggerSearchResultComponent
                .factory()
                .create()

            val viewModel: SearchResultViewModel = viewModel(
                factory = component.viewModelFactory
            )

            SearchResultScreen(
                navController = navController,
                viewModel = viewModel,
                queryParameters = data.queryParameters,
                hazeState = hazeState
            )
        }

        composable<CollectionListRoute> {
            val route = it.toRoute<CollectionListRoute>()
            val component = DaggerCollectionComponent
                .factory()
                .create()

            val viewModel: CollectionListViewModel = viewModel(
                factory = component.viewModelFactory
            )

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
            val component = DaggerMovieListComponent
                .factory()
                .create()

            val viewModel: MovieListViewModel = viewModel(
                factory = component.viewModelFactory
            )

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
            val component = DaggerMovieListComponent
                .factory()
                .create()

            val viewModel: MovieListViewModel = viewModel(
                factory = component.viewModelFactory
            )

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
            val component = DaggerPersonListComponent
                .factory()
                .create()

            val viewModel: PersonListViewModel = viewModel(
                factory = component.viewModelFactory
            )

            PersonPodiumListScreen(
                navController = navController,
                viewModel = viewModel,
                hazeState = hazeState,
                title = route.title,
                queryParameters = route.queryParameters
            )
        }

        composable<PersonRoute> {
            val route = it.toRoute<PersonRoute>()
            val component = DaggerPersonComponent.factory().create()

            val viewModel: PersonViewModel = viewModel(
                factory = component.viewModelFactory
            )

            PersonScreen(
                navController = navController,
                viewModel = viewModel,
                hazeState = hazeState,
                id = route.id
            )
        }

        composable<PersonDetailRoute> {
            val route = it.toRoute<PersonDetailRoute>()
            val component = DaggerPersonComponent.factory().create()

            val viewModel: PersonViewModel = viewModel(
                factory = component.viewModelFactory
            )

            PersonDetailScreen(
                navController = navController,
                viewModel = viewModel,
                id = route.id
            )
        }

        composable<AwardListRoute> {
            val route = it.toRoute<AwardListRoute>()
            val component = DaggerAwardListComponent.factory().create()

            val viewModel: AwardListViewModel = viewModel(
                factory = component.viewModelFactory
            )

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
            val component = DaggerMovieComponent.factory().create()

            val viewModel: MovieViewModel = viewModel(
                factory = component.viewModelFactory
            )

            MovieScreen(
                navController = navController,
                viewModel = viewModel,
                hazeState = hazeState,
                id = route.id
            )
        }
    }
}