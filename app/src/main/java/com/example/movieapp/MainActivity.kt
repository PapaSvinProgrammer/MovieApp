package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.network.KtorClient

class MainActivity : ComponentActivity() {
    private val ktorClient = KtorClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LaunchedEffect(Unit) {
                        Log.d("RRRR", ktorClient.getGenres().toString())
                    }

                    AsyncImage(
                        model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-2ES-FP7y1iOciI3xYJ2ke8Plgec3Q79y2A&s",
                        contentDescription = null,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
