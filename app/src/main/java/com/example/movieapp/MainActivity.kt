package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.app.App
import com.example.movieapp.app.navigation.NavigationGraph
import com.example.movieapp.app.navigation.StartRoute
import com.example.movieapp.di.viewModel.ViewModelFactory
import com.example.movieapp.presentation.screen.MainScreen
import com.example.movieapp.presentation.theme.MovieAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject lateinit var viewModelFactory: ViewModelFactory

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                MainScreen(
                    viewModelFactory = viewModelFactory,
                    startRoute = StartRoute
                )
            }
        }
    }
}
