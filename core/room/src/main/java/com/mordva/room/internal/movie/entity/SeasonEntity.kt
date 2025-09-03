package com.mordva.room.internal.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "season",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movie_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("movie_id")]
)
internal data class SeasonEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "season_id") val seasonId: Long = 0,

    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "en_name") val enName: String?,
    @ColumnInfo(name = "episodes_count") val episodesCount: Int?,
    @ColumnInfo(name = "air_date") val airDate: String?
)

