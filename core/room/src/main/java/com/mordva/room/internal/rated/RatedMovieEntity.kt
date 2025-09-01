package com.mordva.room.internal.rated

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "rated_movies"
)
internal data class RatedMovieEntity(
    @PrimaryKey @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "alternative_name") val alternativeName: String,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "start") val start: Int?,
    @ColumnInfo(name = "end") val end: Int?,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "rating") val rating: Int
)