package com.mordva.room.internal.entities.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "rating",
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
internal data class RatingEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rating_id") val ratingId: Long = 0,

    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "kp") val kp: Float?,
    @ColumnInfo(name = "imdb") val imdb: Float?,
    @ColumnInfo(name = "film_critics") val filmCritics: Float?,
    @ColumnInfo(name = "russian_film_critics") val russianFilmCritics: Float?
)
