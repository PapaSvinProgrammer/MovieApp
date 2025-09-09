package com.mordva.room.internal.entities.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "votes",
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
internal data class VotesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vote_id") val voteId: Long = 0,

    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "kp") val kp: Int?,
    @ColumnInfo(name = "imdb") val imdb: Int?,
    @ColumnInfo(name = "film_critics") val filmCritics: Int?,
    @ColumnInfo(name = "russian_film_critics") val russianFilmCritics: Int?,
    @ColumnInfo(name = "await") val await: Int?
)