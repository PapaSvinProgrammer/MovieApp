package com.example.settings.presentation

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.movieapp.ui.R
import com.example.settings.presentation.widget.content.ChangeThemeContent
import com.example.settings.presentation.widget.content.OtherIconAppContent
import com.example.settings.presentation.widget.row.TwinTitleRow
import com.example.ui.widget.other.TitleTopBarText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getTheme()
        viewModel.getCurrentIconIndex()
    }

    LaunchedEffect(uiState.currentIcon) {
        if (uiState.currentIcon != 1) {
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
            TwinTitleRow(
                checked = uiState.pinCodeSwitch,
                onClick = { viewModel.updatePinSwitch(it) },
                title = stringResource(R.string.parent_control),
                subtitle = stringResource(R.string.enter_with_pin_code),
                description = stringResource(R.string.enter_with_pin_code_description)
            )
            Spacer(modifier = Modifier.height(30.dp))

            OtherIconAppContent(
                checked = uiState.alternativeSwitch,
                onClick = {
                    viewModel.updateAlternativeSwitch(it)

                    if (!it) {
                        viewModel.setCurrentIconIndex(1)
                    }
                },
                currentIconIndex = uiState.currentIcon,
                onChangeIcon = {
                    viewModel.setCurrentIconIndex(it)
                }
            )
            Spacer(modifier = Modifier.height(30.dp))

            ChangeThemeContent(
                themeState = uiState.themeState,
                onChangeTheme = { viewModel.setTheme(it) }
            )
            Spacer(modifier = Modifier.height(30.dp))

            TwinTitleRow(
                checked = uiState.vibrationSwitch,
                onClick = { viewModel.updateVibrationSwitch(it) },
                title = stringResource(R.string.other),
                subtitle = stringResource(R.string.vibration),
                description = stringResource(R.string.vibration_description)
            )
        }
    }
}
