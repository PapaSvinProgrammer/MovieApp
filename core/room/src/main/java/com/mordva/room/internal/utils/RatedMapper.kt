package com.mordva.room.internal.utils

import com.mordva.model.local.RatedMovie
import com.mordva.room.internal.rated.RatedMovieEntity
import com.mordva.room.internal.rated.RatedMovieWithDetails

internal fun RatedMovieWithDetails.toDomain(): RatedMovie {
    return RatedMovie(
        movie = movieWithDetails.toMovie(),
        rating = ratedMovie.rating,
        dateRating = ratedMovie.date
    )
}

internal fun RatedMovie.toEntity(): RatedMovieEntity {
    return RatedMovieEntity(
        movieId = movie.id,
        rating = rating,
        date = dateRating
    )
}

internal fun List<RatedMovieWithDetails>.toDomain() = map { it.toDomain() }