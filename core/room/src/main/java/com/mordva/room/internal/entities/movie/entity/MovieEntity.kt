package com.mordva.room.internal.entities.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
internal data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,

    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "slogan") val slogan: String?,
    @ColumnInfo(name = "year") val year: Int?,
    @ColumnInfo(name = "alternative_name") val alternativeName: String?,
    @ColumnInfo(name = "movie_length") val movieLength: Int?,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "age_rating") val ageRating: Int?,
    @ColumnInfo(name = "rating_mpaa") val ratingMpaa: String?,
    @ColumnInfo(name = "updated_at") val updatedAt: String?,
    @ColumnInfo(name = "short_description") val shortDescription: String?,
    @ColumnInfo(name = "top10") val top10: Int?,
    @ColumnInfo(name = "top250") val top250: Int?,
    @ColumnInfo(name = "is_series") val isSeries: Boolean?,
    @ColumnInfo(name = "series_length") val seriesLength: Int?,
    @ColumnInfo(name = "total_series_length") val totalSeriesLength: Int?
)
