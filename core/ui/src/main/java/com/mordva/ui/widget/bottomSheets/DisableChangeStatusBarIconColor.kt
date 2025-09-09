package com.mordva.ui.widget.bottomSheets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.WindowCompat

@Composable
fun DisableChangeStatusBarIconColor() {
    val view = LocalView.current
    (view.parent as? DialogWindowProvider)?.window?.let { window ->
        SideEffect {
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }
}