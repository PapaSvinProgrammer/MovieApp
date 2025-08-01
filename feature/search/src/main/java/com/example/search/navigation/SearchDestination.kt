package com.example.search.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.navigationroute.CustomNavType
import com.example.navigationroute.MainRoutes
import com.example.navigationroute.SearchRoutes
import com.example.search.searchResult.SearchResultScreen
import com.example.search.searchResult.SearchResultViewModel
import com.example.search.searchResult.di.DaggerSearchResultComponent
import com.example.search.searchScreen.SearchScreen
import com.example.search.searchScreen.SearchViewModel
import com.example.search.searchScreen.di.DaggerSearchComponent
import com.example.search.searchScreen.di.SearchDependency
import com.example.search.searchSettings.SearchSettingsScreen
import com.example.search.searchSettings.SearchSettingsViewModel
import com.example.search.searchSettings.di.DaggerSearchSettingsComponent
import dev.chrisbanes.haze.HazeState
import kotlin.reflect.typeOf

fun NavGraphBuilder.searchDestination(
    dependency: SearchDependency,
    navController: NavController,
    hazeState: HazeState
) {
    composable<MainRoutes.SearchRoute> {
        val component = DaggerSearchComponent
            .factory()
            .create(dependency)

        val viewModel: SearchViewModel = viewModel(
            factory = component.viewModelFactory
        )

        SearchScreen(
            navController = navController,
            viewModel = viewModel,
            hazeState = hazeState
        )
    }

    composable<SearchRoutes.SearchSettingsRoute> {
        val component = DaggerSearchSettingsComponent
            .factory()
            .create(dependency)

        val viewModel: SearchSettingsViewModel = viewModel(
            factory = component.viewModelFactory
        )

        SearchSettingsScreen(
            navController = navController,
            viewModel = viewModel
        )
    }

    composable<SearchRoutes.SearchResultRoute>(
        typeMap = mapOf(
            typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListTypePair
        )
    ) {
        val data = it.toRoute<SearchRoutes.SearchResultRoute>()
        val component = DaggerSearchResultComponent
            .factory()
            .create(dependency)

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
}