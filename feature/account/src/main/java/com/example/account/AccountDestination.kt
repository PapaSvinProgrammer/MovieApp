package com.example.account

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigationroute.AccountRoute

fun NavGraphBuilder.accountDestination(navController: NavController) {
    composable<AccountRoute> {
        AccountScreen(
            navController = navController
        )
    }
}