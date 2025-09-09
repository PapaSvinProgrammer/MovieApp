package com.mordva.model.local

import com.mordva.model.movie.Movie

data class RatedMovie(
    val movie: Movie,
    val rating: Int,
    val dateRating: Long
)
