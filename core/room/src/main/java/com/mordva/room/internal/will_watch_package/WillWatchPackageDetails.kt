package com.mordva.room.internal.will_watch_package

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.room.internal.movie.MovieDetails
import com.mordva.room.internal.movie.entity.MovieEntity

internal data class WillWatchPackageDetails(
    @Embedded val willWatchPackageEntity: WillWatchPackageEntity,

    @Relation(
        parentColumn = "movie_id",
        entityColumn = "id",
        entity = MovieEntity::class
    )
    val movieEntity: MovieDetails
)