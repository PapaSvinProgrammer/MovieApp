package com.mordva.room.internal.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "distributor",
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
internal data class DistributorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "distributor_id") val distributorId: Long = 0,

    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "distributor") val distributor: String?,
    @ColumnInfo(name = "distributor_release") val distributorRelease: String?
)
