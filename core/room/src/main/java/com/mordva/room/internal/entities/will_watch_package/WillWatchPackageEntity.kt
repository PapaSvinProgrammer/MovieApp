package com.mordva.room.internal.entities.will_watch_package

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mordva.room.internal.entities.movie.entity.MovieEntity

@Entity(
    tableName = "will_watch_package",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movie_id"]
        )
    ],
    indices = [Index("movie_id")]
)
internal data class WillWatchPackageEntity(
    @PrimaryKey @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "date") val date: Long
)