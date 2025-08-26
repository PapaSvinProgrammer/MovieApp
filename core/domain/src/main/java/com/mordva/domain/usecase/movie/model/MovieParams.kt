package com.mordva.domain.usecase.movie.model

data class MovieParams(
    val movieId: Int = -1,
    val page: Int = 1,
    val q: String = "",
    val name: String = "",
    val genre: String = ""
)