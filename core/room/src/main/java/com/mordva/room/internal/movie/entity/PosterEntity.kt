package com.mordva.room.internal.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "poster",
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
internal data class PosterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "poster_id") val posterId: Long = 0,

    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "type") val type: String, // poster | backdrop | logo | watchability_logo
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "preview_url") val previewUrl: String?
)
