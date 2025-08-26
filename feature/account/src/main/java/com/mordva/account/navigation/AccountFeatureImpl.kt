package com.mordva.account.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mordva.account.AccountScreen
import com.mordva.navigation.AccountGraph
import com.mordva.navigation.FeatureApi
import com.mordva.profile.navigation.ProfileRoute
import dev.chrisbanes.haze.HazeState

class AccountFeatureImpl : FeatureApi {
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
                AccountScreen(
                    navController = navController,
                    hazeState = hazeState
                )
            }
        }
    }
}