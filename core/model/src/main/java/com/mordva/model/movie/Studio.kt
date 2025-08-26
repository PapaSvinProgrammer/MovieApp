package com.mordva.model.movie

data class Studio(
    val id: String?,
    val subType: String?,
    val title: String?,
    val type: String?,
    val movies: List<Movie>,
)