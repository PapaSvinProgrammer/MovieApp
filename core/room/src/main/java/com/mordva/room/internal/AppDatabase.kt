package com.mordva.room.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mordva.room.internal.history.HistoryDao
import com.mordva.room.internal.history.HistoryEntity
import com.mordva.room.internal.rated.RatedMovieDao
import com.mordva.room.internal.rated.RatedMovieEntity

@Database(
    entities = [
        HistoryEntity::class,
        RatedMovieEntity::class
    ],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao
    abstract fun getRatedDao(): RatedMovieDao
}