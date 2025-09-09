package com.mordva.movieapp.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mordva.movieapp.app.appComponent
import com.mordva.settings.utils.AppTheme
import com.mordva.ui.theme.MovieAppTheme
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: MainViewModel

    @SuppressLint("ContextCastToActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
        setComposeContent()
    }

    private fun setComposeContent() {
        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()
            val isSystemDark = isSystemInDarkTheme()

            val isDarkTheme = when (state.theme) {
                AppTheme.LIGHT -> false
                AppTheme.DARK -> true
                AppTheme.SYSTEM -> isSystemDark
            }

            LaunchedEffect(isDarkTheme) {
                changeSystemBarStyle(isDarkTheme)
            }

            MovieAppTheme(darkTheme = isDarkTheme) {
                state.startRoute?.let { startRoute ->
                    MainScreen(
                        startRoute = startRoute,
                        appComponent = appComponent
                    )
                }
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
