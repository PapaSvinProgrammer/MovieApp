package com.mordva.room.internal.utils

import com.mordva.model.local.MoviePackage
import com.mordva.model.local.PackageParams
import com.mordva.model.movie.Movie
import com.mordva.room.internal.will_watch_package.WillWatchPackageDetails
import com.mordva.room.internal.will_watch_package.WillWatchPackageEntity

internal fun WillWatchPackageDetails.toDomain() = MoviePackage(
    movie = movieEntity.toMovie(),
    date = willWatchPackageEntity.date
)

internal fun List<WillWatchPackageDetails>.toDomain() = map { it.toDomain() }

internal fun PackageParams.toEntity() = WillWatchPackageEntity(
    movieId = id,
    date = date
)

internal fun WillWatchPackageEntity.toDomain() = MoviePackage(
    movie = Movie(),
    date = date
)