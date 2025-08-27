package com.mordva.search.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.navigation.CustomNavType
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.SearchGraph
import com.mordva.search.presentation.searchResult.SearchResultScreen
import com.mordva.search.presentation.searchResult.SearchResultViewModel
import com.mordva.search.presentation.searchScreen.SearchScreen
import com.mordva.search.presentation.searchScreen.SearchViewModel
import com.mordva.search.presentation.searchScreen.di.DaggerSearchComponent
import com.mordva.search.presentation.searchScreen.di.SearchDependency
import com.mordva.search.presentation.searchSettings.SearchSettingsScreen
import com.mordva.search.presentation.searchSettings.SearchSettingsViewModel
import com.mordva.search.presentation.searchSettings.di.DaggerSearchSettingsComponent
import dev.chrisbanes.haze.HazeState
import kotlin.reflect.typeOf

class SearchFeatureImpl(
    private val dependency: SearchDependency
) : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        hazeState: HazeState,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<SearchGraph>(
            startDestination = SearchRoute
        ) {
            composable<SearchRoute> {
                val component = DaggerSearchComponent.factory().create(dependency)
                val viewModel: SearchViewModel = viewModel(
                    factory = component.viewModelFactory
                )

                SearchScreen(
                    navController = navController,
                    viewModel = viewModel,
                    hazeState = hazeState
                )
            }

            composable<SearchResultRoute>(
                typeMap = mapOf(
                    typeOf<ArrayList<Pair<String, String>>>() to CustomNavType.ListTypePair
                )
            ) {
                val data = it.toRoute<SearchResultRoute>()

                val component = DaggerSearchComponent.factory().create(dependency)
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

            composable<SearchSettingsRoute> {
                val component = DaggerSearchSettingsComponent.factory().create(dependency)
                val viewModel: SearchSettingsViewModel = viewModel(
                    factory = component.viewModelFactory
                )

                SearchSettingsScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}