package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.example.movieapp.app.App
import com.example.navigationroute.HomeRoute
import com.example.navigationroute.StartRoute
import com.example.movieapp.di.viewModel.ViewModelFactory
import com.example.movieapp.ui.screen.MainScreen
import com.example.movieapp.ui.theme.MovieAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var viewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ContextCastToActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)

        viewModel.getDarkThemeState()
        viewModel.getEntryState()

        super.onCreate(savedInstanceState)

        setContent {
            MovieAppTheme(darkTheme = viewModel.darkTheme) {
                val startRoute = if (viewModel.isEntry) com.example.navigationroute.HomeRoute else com.example.navigationroute.StartRoute
                val context = LocalContext.current as ComponentActivity

                DisposableEffect(viewModel.darkTheme) {
                    context.enableEdgeToEdge(
                        statusBarStyle = getSystemBarStyle(viewModel.darkTheme),
                        navigationBarStyle = getSystemBarStyle(viewModel.darkTheme)
                    )

                    onDispose {  }
                }

                MainScreen(
                    viewModelFactory = viewModelFactory,
                    startRoute = startRoute
                )
            }
        }
    }

    private fun getSystemBarStyle(isDark: Boolean): SystemBarStyle {
        return when (isDark) {
            true -> SystemBarStyle.dark(Color.Transparent.toArgb())
            false -> SystemBarStyle.light(Color.Transparent.toArgb(), Color.White.toArgb())
        }
    }
}