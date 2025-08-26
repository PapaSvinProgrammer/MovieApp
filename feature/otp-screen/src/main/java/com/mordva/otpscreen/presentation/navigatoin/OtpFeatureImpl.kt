package com.mordva.otpscreen.presentation.navigatoin

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mordva.navigation.FeatureApi
import com.mordva.navigation.OtpGraph
import com.mordva.otpscreen.di.DaggerOtpComponent
import com.mordva.otpscreen.di.OtpDependency
import com.mordva.otpscreen.presentation.CreateOtpScreen
import com.mordva.otpscreen.presentation.DefaultOtpScreen
import com.mordva.otpscreen.presentation.DisableOtpScreen
import com.mordva.otpscreen.presentation.OtpViewModel
import dev.chrisbanes.haze.HazeState

class OtpFeatureImpl(
    private val dependency: OtpDependency
) : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        hazeState: HazeState,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation<OtpGraph>(
            startDestination = OtpGraph.OtpRoute(isCreate = true, isDisable = true)
        ) {
            composable<OtpGraph.OtpRoute> {
                val route = it.toRoute<OtpGraph.OtpRoute>()

                val component = DaggerOtpComponent.factory().create(dependency)
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
    }
}