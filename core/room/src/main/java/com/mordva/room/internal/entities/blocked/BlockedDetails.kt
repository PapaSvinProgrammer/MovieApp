package com.mordva.room.internal.entities.blocked

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.room.internal.entities.movie.MovieDetails
import com.mordva.room.internal.entities.movie.entity.MovieEntity

internal data class BlockedDetails(
    @Embedded val blockedEntity: BlockedEntity,

    @Relation(
        entity = MovieEntity::class,
        parentColumn = "movie_id",
        entityColumn = "id"
    )
    val movieDetails: MovieDetails
)