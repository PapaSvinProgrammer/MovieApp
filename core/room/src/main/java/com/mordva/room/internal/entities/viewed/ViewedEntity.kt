package com.mordva.room.internal.entities.viewed

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mordva.room.internal.entities.movie.entity.MovieEntity

@Entity(
    tableName = "viewed",
    indices = [Index("movie_id")],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movie_id"]
        )
    ]
)
internal data class ViewedEntity(
    @PrimaryKey @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "date") val date: Long
)