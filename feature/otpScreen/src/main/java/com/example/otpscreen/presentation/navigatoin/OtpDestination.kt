package com.example.otpscreen.presentation.navigatoin

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.corecomponent.AppComponent
import com.example.navigationroute.OtpRoute
import com.example.otpscreen.di.DaggerOtpComponent
import com.example.otpscreen.presentation.CreateOtpScreen
import com.example.otpscreen.presentation.DefaultOtpScreen
import com.example.otpscreen.presentation.DisableOtpScreen
import com.example.otpscreen.presentation.OtpViewModel

fun NavGraphBuilder.otpDestination(
    navController: NavController,
    appComponent: AppComponent
) {
    composable<OtpRoute> {
        val component = remember {
            DaggerOtpComponent
                .factory()
                .create(appComponent)
        }

        val route = it.toRoute<OtpRoute>()

        val viewModel: OtpViewModel = viewModel(
            factory = component.viewModelFactory
        )

        if (route.isDisable) {
            DisableOtpScreen(
                navController = navController,
                viewModel = viewModel
            )
        } else {
            if (route.isCreate) {
                CreateOtpScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            } else {
                DefaultOtpScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}