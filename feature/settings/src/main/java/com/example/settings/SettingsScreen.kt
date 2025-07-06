package com.example.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.movieapp.settings.R
import com.example.settings.widget.content.OtherIconAppContent
import com.example.settings.widget.content.ChangeThemeContent
import com.example.settings.widget.row.TwinTitleRow
import com.example.ui.widget.other.TitleTopBarText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel
) {
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getTheme()
        viewModel.getCurrentIconIndex()
    }

    LaunchedEffect(viewModel.currentIcon) {
        if (viewModel.currentIcon != 1) {
            viewModel.updateAlternativeSwitch(true)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(R.string.settings))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                    start = 15.dp,
                    end = 15.dp
                )
        ) {
            Text(
                text = stringResource(R.string.parent_control),
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            TwinTitleRow(
                checked = viewModel.pinCodeSwitch,
                onClick = { viewModel.updatePinSwitch(it) },
                title = stringResource(R.string.enter_with_pin_code),
                description = stringResource(R.string.enter_with_pin_code_description)
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.icon_app),
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            OtherIconAppContent(
                checked = viewModel.alternativeIconSwitch,
                onClick = {
                    viewModel.updateAlternativeSwitch(it)

                    if (!it) {
                        viewModel.setCurrentIconIndex(1)
                    }
                },
                currentIconIndex = viewModel.currentIcon,
                onChangeIcon = {
                    viewModel.setCurrentIconIndex(it)
                }
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.theme),
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            ChangeThemeContent(
                isDark = viewModel.isDark,
                onChangeTheme = { viewModel.setTheme(it) }
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.other),
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            TwinTitleRow(
                checked = viewModel.vibrationSwitch,
                onClick = { viewModel.updateVibrationSwitch(it) },
                title = stringResource(R.string.vibration),
                description = stringResource(R.string.vibration_description)
            )
        }
    }
}
