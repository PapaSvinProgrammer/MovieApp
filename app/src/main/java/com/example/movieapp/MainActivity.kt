package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.movieapp.app.App
import com.example.movieapp.app.navigation.StartRoute
import com.example.movieapp.di.viewModel.ViewModelFactory
import com.example.movieapp.presentation.screen.MainScreen
import com.example.movieapp.presentation.theme.MovieAppTheme
import com.example.movieapp.presentation.theme.colorScheme.ColorSchemeType
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject lateinit var viewModelFactory: ViewModelFactory

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.Transparent.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(Color.Transparent.toArgb())
        )
        setContent {
            MovieAppTheme(
                colorSchemeType = ColorSchemeType.DEFAULT,
                darkTheme = true
            ) {
                MainScreen(
                    viewModelFactory = viewModelFactory,
                    startRoute = StartRoute
                )
            }
        }
    }
}
