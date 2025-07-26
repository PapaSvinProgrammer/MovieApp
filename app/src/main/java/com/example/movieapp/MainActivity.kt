package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.example.navigationroute.HomeRoute
import com.example.navigationroute.StartRoute
import com.example.settings.utils.AppTheme
import com.example.settings.utils.ThemeObservable
import com.example.settings.utils.ThemeObserver
import com.example.ui.theme.MovieAppTheme

class MainActivity : AppCompatActivity(), ThemeObserver {
    @SuppressLint("ContextCastToActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeObservable.subscribe(this)

        setContent {
            MovieAppTheme {
                MainScreen(
                    startRoute = StartRoute,
                    appComponent = appComponent
                )
            }
        }
    }

    override fun onThemeChanged(theme: AppTheme) {
        when (theme) {
            AppTheme.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            AppTheme.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            AppTheme.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    override fun onDestroy() {
        ThemeObservable.unsubscribe(this)
        super.onDestroy()
    }
}