package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.room.AppDatabase
import com.example.core.data.room.dao.HistoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DATABASE_NAME = "movie_app_database"

@Module
interface RoomModule {
    companion object {
        @Provides
        @Singleton
        fun provideAppDatabase(context: Context): AppDatabase {
            val db = Room.databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = DATABASE_NAME
            ).build()

            return db
        }

        @Provides
        @Singleton
        fun provideHistoryDao(db: AppDatabase): HistoryDao {
            return db.getHistoryDao()
        }
    }
}