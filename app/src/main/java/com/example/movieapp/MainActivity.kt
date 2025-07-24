package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.navigationroute.StartRoute
import com.example.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            MovieAppTheme {
//                val startRoute = if (viewModel.isEntry) com.example.navigationroute.HomeRoute else com.example.navigationroute.StartRoute
//                val context = LocalContext.current as ComponentActivity
//
//                DisposableEffect(viewModel.darkTheme) {
//                    context.enableEdgeToEdge(
//                        statusBarStyle = getSystemBarStyle(viewModel.darkTheme),
//                        navigationBarStyle = getSystemBarStyle(viewModel.darkTheme)
//                    )
//
//                    onDispose {  }
//                }

                MainScreen(startRoute = StartRoute)
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