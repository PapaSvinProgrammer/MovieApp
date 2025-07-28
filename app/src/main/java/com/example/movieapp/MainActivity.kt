package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.lifecycleScope
import com.example.navigationroute.StartRoute
import com.example.settings.utils.AppTheme
import com.example.settings.utils.ThemeObservable
import com.example.settings.utils.ThemeObserver
import com.example.settings.utils.toAppTheme
import com.example.ui.theme.MovieAppTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ThemeObserver {
    private var currentTheme: AppTheme = AppTheme.SYSTEM

    @SuppressLint("ContextCastToActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeObservable.subscribe(this)

        lifecycleScope.launch {
            val theme = appComponent.preferencesRepository.getThemeState().first()
            onThemeChanged(theme.toAppTheme())
            setComposeContent()
        }
    }

    private fun setComposeContent() {
        setContent {
            val isSystemDark = isSystemInDarkTheme()

            val isDarkTheme = when (currentTheme) {
                AppTheme.LIGHT -> false
                AppTheme.DARK -> true
                AppTheme.SYSTEM -> isSystemDark
            }

            LaunchedEffect(isDarkTheme) {
                changeSystemBarStyle(isDarkTheme)
            }

            MovieAppTheme {
                MainScreen(
                    startRoute = StartRoute,
                    appComponent = appComponent
                )
            }
        }
    }

    override fun onThemeChanged(theme: AppTheme) {
        currentTheme = theme
        when (theme) {
            AppTheme.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            AppTheme.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            AppTheme.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    private fun changeSystemBarStyle(isDark: Boolean) {
        enableEdgeToEdge(
            statusBarStyle = getSystemBarStyle(isDark),
            navigationBarStyle = getSystemBarStyle(isDark)
        )
    }

    override fun onDestroy() {
        ThemeObservable.unsubscribe(this)
        super.onDestroy()
    }
}

private fun getSystemBarStyle(isDark: Boolean): SystemBarStyle {
    return when (isDark) {
        true -> SystemBarStyle.dark(Color.Transparent.toArgb())
        false -> SystemBarStyle.light(Color.Transparent.toArgb(), Color.White.toArgb())
    }
}
