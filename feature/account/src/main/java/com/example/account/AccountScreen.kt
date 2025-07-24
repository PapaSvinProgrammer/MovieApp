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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieapp.account.R
import com.example.navigationroute.AboutAppRoute
import com.example.navigationroute.SettingsRoute
import com.example.ui.theme.ColorGradient1
import com.example.ui.theme.ColorGradient2
import com.example.ui.theme.ColorGradient3
import com.example.ui.theme.ColorGradient4
import com.example.ui.widget.component.TitleRow
import com.example.ui.widget.other.animatedBorder

@Composable
internal fun AccountScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            ProfileContent(
                name = "Урайкин Роман",
                email = "UraykinRab@yandex.ru"
            )

            Spacer(modifier = Modifier.height(30.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(30.dp))

            SettingsContent(
                onSettings = {
                    navController.navigate(SettingsRoute) { launchSingleTop = true }
                },
                onSupport = {},
                onAbout = {
                    navController.navigate(AboutAppRoute) { launchSingleTop = true }
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

@Composable
private fun SettingsContent(
    onSettings: () -> Unit,
    onSupport: () -> Unit,
    onAbout: () -> Unit
) {
    TitleRow(
        title = stringResource(R.string.settings),
        fontSize = 15.sp,
        onClick = onSettings
    )

    TitleRow(
        title = stringResource(R.string.chat_with_support),
        fontSize = 15.sp,
        onClick = onSupport
    )

    TitleRow(
        title = stringResource(R.string.about_app),
        fontSize = 15.sp,
        onClick = onAbout
    )
}