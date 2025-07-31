package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.navigationroute.StartRoute
import com.example.settings.utils.AppTheme
import com.example.settings.utils.toAppTheme
import com.example.ui.theme.MovieAppTheme

class MainActivity : AppCompatActivity() {
    @SuppressLint("ContextCastToActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setComposeContent()
    }

    private fun setComposeContent() {
        setContent {
            val theme = appComponent
                .preferencesRepository
                .getThemeState()
                .collectAsStateWithLifecycle(1)
            val isSystemDark = isSystemInDarkTheme()

            val isDarkTheme = when (theme.value.toAppTheme()) {
                AppTheme.LIGHT -> false
                AppTheme.DARK -> true
                AppTheme.SYSTEM -> isSystemDark
            }

            LaunchedEffect(isDarkTheme) {
                changeSystemBarStyle(isDarkTheme)
            }

            MovieAppTheme(
                darkTheme = isDarkTheme
            ) {
                MainScreen(
                    startRoute = StartRoute,
                    appComponent = appComponent
                )
            }
        }
    }

    private fun changeSystemBarStyle(isDark: Boolean) {
        enableEdgeToEdge(
            statusBarStyle = getSystemBarStyle(isDark),
            navigationBarStyle = getSystemBarStyle(isDark)
        )
    }
}

private fun getSystemBarStyle(isDark: Boolean): SystemBarStyle {
    return when (isDark) {
        true -> SystemBarStyle.dark(Color.Transparent.toArgb())
        false -> SystemBarStyle.light(Color.Transparent.toArgb(), Color.White.toArgb())
    }
}
