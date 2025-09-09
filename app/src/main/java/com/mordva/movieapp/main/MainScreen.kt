package com.mordva.movieapp.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mordva.aboutapp.navigation.AboutAppFeatureImpl
import com.mordva.account.presentation.navigation.AccountFeatureImpl
import com.mordva.awardlist.presentation.navigation.AwardListFeatureImpl
import com.mordva.collectionlist.presentation.navigation.CollectionListFeatureImpl
import com.mordva.favorite.navigation.FavoriteFeatureImpl
import com.mordva.home.presentation.navigation.HomeFeatureImpl
import com.mordva.images_list.presentation.navigation.ImageListFeatureImpl
import com.mordva.login.presentation.navigation.LoginFeatureImpl
import com.mordva.movieScreen.presentation.navigation.MovieFeatureImpl
import com.mordva.movieapp.app.AppNavigation
import com.mordva.movieapp.di.AppComponent
import com.mordva.movielist.navigation.MovieListFeatureImpl
import com.mordva.navigation.RootGraph
import com.mordva.otpscreen.presentation.navigatoin.OtpFeatureImpl
import com.mordva.personpodium.navigation.PersonPodiumListFeatureImpl
import com.mordva.personscreen.presentation.navigation.PersonFeatureImpl
import com.mordva.search.presentation.navigation.SearchFeatureImpl
import com.mordva.settings.presentation.navigation.SettingsFeatureImpl
import com.mordva.ui.widget.navigation.BottomBarItems
import com.mordva.ui.widget.navigation.HazeBottomBar
import dev.chrisbanes.haze.rememberHazeState

@Composable
fun MainScreen(
    startRoute: RootGraph,
    appComponent: AppComponent
) {
    val features = listOf(
        AccountFeatureImpl(appComponent),
        HomeFeatureImpl(appComponent, appComponent),
        AboutAppFeatureImpl(),
        AwardListFeatureImpl(appComponent),
        CollectionListFeatureImpl(appComponent),
        FavoriteFeatureImpl(),
        MovieFeatureImpl(appComponent),
        MovieListFeatureImpl(appComponent),
        SearchFeatureImpl(appComponent),
        OtpFeatureImpl(appComponent),
        PersonFeatureImpl(appComponent),
        PersonPodiumListFeatureImpl(appComponent),
        SettingsFeatureImpl(appComponent),
        AboutAppFeatureImpl(),
        LoginFeatureImpl(appComponent),
        ImageListFeatureImpl(appComponent)
    )

    var bottomBarVisible by remember { mutableStateOf(false) }
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    bottomBarIsVisibility(
        route = currentRoute,
        onResult = { bottomBarVisible = it }
    )

    val hazeState = rememberHazeState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            HazeBottomBar(
                tabs = BottomBarItems.items,
                hazeState = hazeState,
                navController = navController,
                visible = bottomBarVisible
            )
        }
    ) { _ ->
        AppNavigation(
            navController = navController,
            startRoute = startRoute,
            hazeState = hazeState,
            list = features
        )
    }
}

private fun bottomBarIsVisibility(route: String?, onResult: (Boolean) -> Unit) {
    when (route) {
        "com.mordva.login.presentation.navigation.LoginRoute" -> onResult(false)
        "com.mordva.search.presentation.navigation.SearchSettingsRoute" -> onResult(false)
        else -> onResult(true)
    }
}