package com.example.room.internal.di

import android.content.Context
import androidx.room.Room
import com.example.room.external.HistoryDao
import com.example.room.internal.AppDatabase
import dagger.Module
import dagger.Provides

private const val DATABASE_NAME = "movie_app_database"


@Module
interface RoomModule {
    companion object {
        @Provides
        fun provideAppDatabase(context: Context): AppDatabase {
            val db = Room.databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = DATABASE_NAME
            ).build()

            return db
        }

        @Provides
        fun provideHistoryDao(db: AppDatabase): HistoryDao {
            return db.getHistoryDao()
        }
    }
}