package com.mordva.room.internal.favorite_package

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.room.internal.movie.MovieDetails
import com.mordva.room.internal.movie.entity.MovieEntity

internal data class FavoritePackageDetails(
    @Embedded val favoritePackageEntity: FavoritePackageEntity,

    @Relation(
        entity = MovieEntity::class,
        entityColumn = "id",
        parentColumn = "movie_id"
    )
    val movie: MovieDetails
)