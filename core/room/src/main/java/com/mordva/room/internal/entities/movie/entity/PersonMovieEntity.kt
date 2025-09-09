package com.mordva.room.internal.entities.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "person_movie",
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
internal data class PersonMovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,

    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "en_name") val enName: String?,
    @ColumnInfo(name = "photo") val photo: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "profession") val profession: String?,
    @ColumnInfo(name = "en_profession") val enProfession: String?
)
