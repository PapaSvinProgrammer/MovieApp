package com.mordva.room.internal.entities.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "premiere",
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = ["id"],
        childColumns = ["movie_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("movie_id")]
)
internal data class PremiereEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "premiere_id") val premiereId: Long = 0,

    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "bluray") val bluray: String?,
    @ColumnInfo(name = "digital") val digital: String?,
    @ColumnInfo(name = "dvd") val dvd: String?,
    @ColumnInfo(name = "russia") val russia: String?,
    @ColumnInfo(name = "world") val world: String?
)