package com.mordva.movieScreen.domain.model

import com.mordva.model.movie.Movie

internal class RatedMovieParams(
    val movie: Movie,
    val rating: Int
)