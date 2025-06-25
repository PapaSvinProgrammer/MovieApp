package com.example.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "search_history",
    indices = [
        Index(
            value = ["movie_id"],
            unique = true
        )
    ]
)
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "alternative_name") val alternativeName: String,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "start") val start: Int?,
    @ColumnInfo(name = "end") val end: Int?,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "isMovie") val isMovie: Boolean
)