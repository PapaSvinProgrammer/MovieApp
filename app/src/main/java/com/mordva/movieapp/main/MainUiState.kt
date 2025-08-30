package com.mordva.movieapp.main

import com.mordva.navigation.RootGraph
import com.mordva.settings.utils.AppTheme

data class MainUiState(
    val theme: AppTheme = AppTheme.SYSTEM,
    val startRoute: RootGraph? = null
)