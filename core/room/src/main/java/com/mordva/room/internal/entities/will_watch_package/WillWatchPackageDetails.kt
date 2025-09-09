package com.mordva.room.internal.entities.will_watch_package

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.room.internal.entities.movie.MovieDetails
import com.mordva.room.internal.entities.movie.entity.MovieEntity

internal data class WillWatchPackageDetails(
    @Embedded val willWatchPackageEntity: WillWatchPackageEntity,

    @Relation(
        parentColumn = "movie_id",
        entityColumn = "id",
        entity = MovieEntity::class
    )
    val movie: MovieDetails
)