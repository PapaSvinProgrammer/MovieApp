package com.mordva.room.internal.utils

import com.mordva.model.local.RatedMovie
import com.mordva.room.internal.rated.RatedMovieEntity

internal fun RatedMovieEntity.toDomain(): RatedMovie =
    RatedMovie(
        movieId = movieId,
        name = name,
        alternativeName = alternativeName,
        year = year,
        start = start,
        end = end,
        poster = poster,
        rating = rating
    )

internal fun RatedMovie.toEntity(): RatedMovieEntity =
    RatedMovieEntity(
        movieId = movieId,
        name = name,
        alternativeName = alternativeName,
        year = year,
        start = start,
        end = end,
        poster = poster,
        rating = rating
    )

internal fun List<RatedMovieEntity>.toDomain() = map { it.toDomain() }