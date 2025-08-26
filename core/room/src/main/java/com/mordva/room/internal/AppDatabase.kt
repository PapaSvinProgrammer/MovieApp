package com.mordva.room.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mordva.room.internal.history.HistoryDao
import com.mordva.room.internal.history.HistoryEntity

@Database(
    entities = [HistoryEntity::class],
    version = 1
)
internal abstract class AppDatabase: RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao
}