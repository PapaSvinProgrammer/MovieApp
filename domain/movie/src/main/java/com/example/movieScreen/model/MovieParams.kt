package com.example.movieScreen.model

data class MovieParams(
    val movieId: Int = -1,
    val page: Int = 1,
    val q: String = ""
)