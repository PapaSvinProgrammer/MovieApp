package com.mordva.room.internal.entities.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "genres",
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
internal data class GenreEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "genre_id") val genreId: Long = 0,

    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "slug") val slug: String
)

