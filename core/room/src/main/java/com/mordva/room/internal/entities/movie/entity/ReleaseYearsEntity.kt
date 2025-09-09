package com.mordva.room.internal.entities.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "release_years",
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
internal data class ReleaseYearsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rel_id") val relId: Long = 0,

    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "start") val start: Int?,
    @ColumnInfo(name = "end") val end: Int?
)
