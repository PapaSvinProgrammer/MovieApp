package com.mordva.room.internal.movie.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "episode",
    foreignKeys = [
        ForeignKey(
            entity = SeasonEntity::class,
            parentColumns = ["season_id"],
            childColumns = ["season_owner_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("season_owner_id")]
)
internal data class EpisodeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "episode_id") val episodeId: Long = 0,

    @ColumnInfo(name = "season_owner_id") val seasonOwnerId: Long,
    @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "en_name") val enName: String?,
    @ColumnInfo(name = "air_date") val airDate: String?
)