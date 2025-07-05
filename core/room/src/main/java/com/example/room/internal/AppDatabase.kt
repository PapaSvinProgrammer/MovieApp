package com.example.room.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room.external.HistoryDao

@Database(
    entities = [HistoryEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao
}