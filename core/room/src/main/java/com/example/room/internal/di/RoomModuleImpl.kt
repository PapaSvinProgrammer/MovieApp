package com.example.room.internal.di

import android.content.Context
import androidx.room.Room
import com.example.room.external.HistoryDao
import com.example.room.internal.AppDatabase
import com.example.utils.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
internal interface RoomModuleImpl {
    companion object {
        private const val DATABASE_NAME = "movie_app_database"

        @Provides
        @ApplicationScope
        fun provideAppDatabase(context: Context): AppDatabase {
            val db = Room.databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = DATABASE_NAME
            ).build()

            return db
        }

        @Provides
        @ApplicationScope
        fun provideHistoryDao(db: AppDatabase): HistoryDao {
            return db.getHistoryDao()
        }
    }
}
