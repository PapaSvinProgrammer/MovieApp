package com.mordva.room.internal.utils

import com.mordva.model.local.MoviePackage
import com.mordva.model.local.PackageParams
import com.mordva.model.movie.Movie
import com.mordva.room.internal.entities.favorite_package.FavoritePackageDetails
import com.mordva.room.internal.entities.favorite_package.FavoritePackageEntity

internal fun FavoritePackageDetails.toDomain() = MoviePackage(
    movie = movie.toMovie(),
    date = favoritePackageEntity.date
)

internal fun List<FavoritePackageDetails>.toDomain() = map { it.toDomain() }

internal fun PackageParams.toFavoritePackageEntity() = FavoritePackageEntity(
    movieId = id,
    date = date
)

internal fun FavoritePackageEntity.toDomain() = MoviePackage(
    movie = Movie(),
    date = date
)