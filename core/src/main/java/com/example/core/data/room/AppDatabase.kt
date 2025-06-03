package com.example.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.room.dao.HistoryDao
import com.example.core.data.room.entity.HistoryEntity

@Database(
    entities = [HistoryEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao
}