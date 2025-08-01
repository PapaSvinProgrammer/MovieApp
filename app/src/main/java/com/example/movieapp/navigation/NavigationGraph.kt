package com.example.movieapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.aboutapp.aboutAppDestination
import com.example.account.accountDestination
import com.example.awardlist.presentation.navigation.awardListDestination
import com.example.collectionlist.presentation.navigation.collectionListDestination
import com.example.favorite.favoriteDestination
import com.example.home.presentation.navigation.homeDestination
import com.example.login.presentation.navigation.loginDestination
import com.example.movieScreen.presentation.navigation.movieDestination
import com.example.movieapp.AppComponent
import com.example.movielist.movieListDestination
import com.example.navigationroute.MovieRoutes
import com.example.navigationroute.NavRoute
import com.example.otpscreen.presentation.navigatoin.otpDestination
import com.example.personpodium.personPodiumListDestination
import com.example.personscreen.presentation.navigation.personDestination
import com.example.search.navigation.searchDestination
import com.example.settings.presentation.navigation.settingsDestination
import dev.chrisbanes.haze.HazeState

@Composable
fun NavigationGraph(
    navController: NavHostController,
    appComponent: AppComponent,
    hazeState: HazeState,
    startRoute: NavRoute
) {
    NavHost(
        navController = navController,
        startDestination = MovieRoutes.MovieRoute(689),
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
        aboutAppDestination(
            navController = navController
        )

        accountDestination(
            navController = navController,
            hazeState = hazeState
        )

        awardListDestination(
            dependency = appComponent,
            navController = navController,
            hazeState = hazeState
        )

        collectionListDestination(
            navController = navController,
            dependency = appComponent,
            hazeState = hazeState
        )

        favoriteDestination(
            navController = navController
        )

        homeDestination(
            homeDependency = appComponent,
            movieListDependency = appComponent,
            navController = navController,
            hazeState = hazeState
        )


        loginDestination(
            navController = navController
        )

        movieDestination(
            movieDependency = appComponent,
            navController = navController,
            hazeState = hazeState
        )

        movieListDestination(
            dependency = appComponent,
            navController = navController,
            hazeState = hazeState
        )

        personDestination(
            dependency = appComponent,
            navController = navController,
            hazeState = hazeState
        )

        personPodiumListDestination(
            dependency = appComponent,
            navController = navController,
            hazeState = hazeState
        )

        searchDestination(
            dependency = appComponent,
            navController = navController,
            hazeState = hazeState
        )

        settingsDestination(
            dependency = appComponent,
            navController = navController
        )

        otpDestination(
            navController = navController,
            dependency = appComponent
        )
    }
}
