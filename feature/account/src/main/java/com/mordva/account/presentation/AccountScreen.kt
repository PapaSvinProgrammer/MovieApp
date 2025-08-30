package com.mordva.account.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieapp.ui.R
import com.mordva.account.presentation.widget.SettingsList
import com.mordva.navigation.AboutAppGraph
import com.mordva.navigation.SettingsGraph
import com.mordva.ui.theme.ColorGradient1
import com.mordva.ui.theme.ColorGradient2
import com.mordva.ui.theme.ColorGradient3
import com.mordva.ui.theme.ColorGradient4
import com.mordva.ui.widget.other.animatedBorder
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@Composable
internal fun AccountScreen(
    navController: NavController,
    viewModel: AccountViewModel,
    hazeState: HazeState
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .hazeSource(hazeState)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            ProfileContent(
                name = "Урайкин Роман",
                email = "UraykinRab@yandex.ru"
            )

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

@Composable
private fun ColumnScope.ProfileContent(
    name: String,
    email: String
) {
    Box(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .animatedBorder(
                borderColors = listOf(
                    ColorGradient1,
                    ColorGradient2,
                    ColorGradient3,
                    ColorGradient4
                ),
                backgroundColor = MaterialTheme.colorScheme.background,
                borderWidth = 2.dp
            )
            .padding(20.dp)
    ) {
        Image(
            modifier = Modifier.size(80.dp),
            painter = painterResource(R.drawable.ic_duck),
            contentDescription = null
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = name,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )

    Spacer(modifier = Modifier.height(5.dp))

    Text(
        text = email,
        fontSize = 14.sp,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
}
