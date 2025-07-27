package com.example.otpscreen.presentation

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.movieapp.ui.R
import com.example.otpscreen.presentation.widget.content.MainOtpContent
import com.example.otpscreen.utils.actionHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DisableOtpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: OtpViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val defaultCode by viewModel.defaultCode.collectAsStateWithLifecycle()

    val focusRequesters = remember { List(4) { FocusRequester() } }
    val keyboardManager = LocalSoftwareKeyboardController.current

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        focusRequesters[0].requestFocus()
        viewModel.getDefaultPinCode()
    }

    LaunchedEffect(focusRequesters) {
        focusRequesters[0].requestFocus()
    }

    LaunchedEffect(state.isValid) {
        if (state.isValid == true) {
            navController.popBackStack()
        }
    }

    LaunchedEffect(state.focusedIndex) {
        state.focusedIndex?.let { index ->
            focusRequesters.getOrNull(index)?.requestFocus()
        }
    }

    LaunchedEffect(state.code, keyboardManager) {
        val allNumbersEntered = state.code.none { it == null }

        if (allNumbersEntered) {
            focusRequesters.forEach { it.freeFocus() }
            focusRequesters[0].requestFocus()

            if (state.code.joinToString("") != defaultCode) {
                viewModel.updateValidState(false)
                viewModel.resetCode()
            }
            else {
                viewModel.deleteDefaultPinCode()
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.reset_pin_code)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        MainOtpContent(
            state = state,
            focusRequesters = focusRequesters,
            onAction = { action ->
                if (state.isValid == false) {
                    viewModel.updateValidState(null)
                }

                actionHandler(
                    action = action,
                    viewModel = viewModel,
                    focusRequesters = focusRequesters
                )
            },
            modifier = modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        )
    }
}
