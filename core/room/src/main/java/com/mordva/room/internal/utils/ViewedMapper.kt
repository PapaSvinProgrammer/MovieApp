package com.mordva.room.internal.utils

import com.mordva.model.local.MoviePackage
import com.mordva.model.local.PackageParams
import com.mordva.model.movie.Movie
import com.mordva.room.internal.entities.viewed.ViewedDetails
import com.mordva.room.internal.entities.viewed.ViewedEntity

internal fun ViewedDetails.toDomain() = MoviePackage(
    movie = movieDetails.toMovie(),
    date = viewedEntity.date
)

internal fun List<ViewedDetails>.toDomain() = map { it.toDomain() }

internal fun PackageParams.toViewedEntity() = ViewedEntity(
    movieId = id,
    date = date
)

internal fun ViewedEntity.toDomain() = MoviePackage(
    movie = Movie(),
    date = date
)