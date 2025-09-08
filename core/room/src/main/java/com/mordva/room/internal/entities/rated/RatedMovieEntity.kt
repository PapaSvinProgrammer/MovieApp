package com.mordva.room.internal.entities.rated

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mordva.room.internal.entities.movie.entity.MovieEntity

@Entity(
    tableName = "rated_movies",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movie_id"]
        )
    ],
    indices = [Index("movie_id")]
)
internal data class RatedMovieEntity(
    @PrimaryKey @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "rating") val rating: Int,
    @ColumnInfo(name = "date") val date: Long
)