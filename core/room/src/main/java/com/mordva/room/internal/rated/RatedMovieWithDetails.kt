package com.mordva.room.internal.rated

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.room.internal.movie.MovieWithDetails
import com.mordva.room.internal.movie.entity.MovieEntity

internal data class RatedMovieWithDetails(
    @Embedded val ratedMovie: RatedMovieEntity,

    @Relation(
        parentColumn = "movie_id",
        entityColumn = "id",
        entity = MovieEntity::class
    )
    val movieWithDetails: MovieWithDetails
)