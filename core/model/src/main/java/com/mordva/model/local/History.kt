package com.mordva.model.local

data class History(
    val id: Int = 0,
    val movieId: Int,
    val name: String,
    val alternativeName: String,
    val year: Int,
    val start: Int?,
    val end: Int?,
    val poster: String,
    val isMovie: Boolean
)