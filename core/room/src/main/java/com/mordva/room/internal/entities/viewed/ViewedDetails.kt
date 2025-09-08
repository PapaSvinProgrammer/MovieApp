package com.mordva.room.internal.entities.viewed

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.room.internal.entities.movie.MovieDetails
import com.mordva.room.internal.entities.movie.entity.MovieEntity

internal data class ViewedDetails(
    @Embedded val viewedEntity: ViewedEntity,

    @Relation(
        entity = MovieEntity::class,
        entityColumn = "id",
        parentColumn = "movie_id"
    )
    val movieDetails: MovieDetails
)