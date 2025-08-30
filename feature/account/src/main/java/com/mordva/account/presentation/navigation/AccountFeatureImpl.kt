package com.mordva.account.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mordva.account.di.AccountDependency
import com.mordva.account.di.DaggerAccountComponent
import com.mordva.account.presentation.AccountScreen
import com.mordva.account.presentation.AccountViewModel
import com.mordva.navigation.AccountGraph
import com.mordva.navigation.FeatureApi
import com.mordva.profile.navigation.ProfileRoute
import dev.chrisbanes.haze.HazeState

class AccountFeatureImpl(
    private val dependency: AccountDependency
) : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        hazeState: HazeState,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<AccountGraph>(
            startDestination = ProfileRoute
        ) {
            composable<ProfileRoute> {
                val component = DaggerAccountComponent.factory().create(dependency)
                val viewModel: AccountViewModel = viewModel(
                    factory = component.viewModelFactory
                )

                AccountScreen(
                    navController = navController,
                    viewModel = viewModel,
                    hazeState = hazeState,
                )
            }
        }
    }
}