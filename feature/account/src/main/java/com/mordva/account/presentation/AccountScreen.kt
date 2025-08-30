package com.mordva.account.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mordva.account.presentation.widget.content.ProfileContent
import com.mordva.account.presentation.widget.content.SettingsList
import com.mordva.navigation.AboutAppGraph
import com.mordva.navigation.SettingsGraph
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@Composable
internal fun AccountScreen(
    navController: NavController,
    viewModel: AccountViewModel,
    hazeState: HazeState
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .hazeSource(hazeState)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            ProfileContent(state.userAccount)

            Spacer(modifier = Modifier.height(30.dp))
            HorizontalDivider()

            SettingsList(
                onSupport = {},
                onAbout = {
                    navController.navigate(AboutAppGraph) {
                        launchSingleTop = true
                    }
                },
                onSound = {
                    navController.navigate(SettingsGraph.SoundRoute) {
                        launchSingleTop = true
                    }
                },
                onConf = {
                    navController.navigate(SettingsGraph.ConfidentialRoute) {
                        launchSingleTop = true
                    }
                },
                onLanguage = {},
                onData = {},
                onDecor = {
                    navController.navigate(SettingsGraph.DecorRoute) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
