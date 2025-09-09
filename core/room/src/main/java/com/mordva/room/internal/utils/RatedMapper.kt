package com.mordva.room.internal.utils

import com.mordva.model.local.RatedMovie
import com.mordva.model.movie.Movie
import com.mordva.room.internal.entities.rated.RatedMovieDetails
import com.mordva.room.internal.entities.rated.RatedMovieEntity

internal fun RatedMovieDetails.toDomain(): RatedMovie {
    return RatedMovie(
        movie = movieDetails.toMovie(),
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

internal fun List<RatedMovieDetails>.toDomain() = map { it.toDomain() }

internal fun RatedMovieEntity.toDomain() = RatedMovie(
    movie = Movie(),
    rating = rating,
    dateRating = date
)