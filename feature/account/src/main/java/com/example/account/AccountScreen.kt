package com.example.account

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
import com.example.navigationroute.SettingsRoutes
import com.example.ui.theme.ColorGradient1
import com.example.ui.theme.ColorGradient2
import com.example.ui.theme.ColorGradient3
import com.example.ui.theme.ColorGradient4
import com.example.ui.widget.other.animatedBorder
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@Composable
internal fun AccountScreen(
    navController: NavController,
    hazeState: HazeState
) {
    Scaffold(
        topBar = {
        }
    ) { innerPadding ->
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
                    navController.navigate(SettingsRoutes.AboutAppRoute) {
                        launchSingleTop = true
                    }
                },
                onSound = {},
                onConf = {},
                onLanguage = {},
                onData = {},
                onDecor = {
                    navController.navigate(SettingsRoutes.DecorRoute) {
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
