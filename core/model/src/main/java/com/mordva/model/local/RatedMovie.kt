package com.mordva.model.local

data class RatedMovie(
    val movieId: Int,
    val name: String,
    val alternativeName: String,
    val year: Int,
    val start: Int?,
    val end: Int?,
    val poster: String,
    val rating: Int
)
