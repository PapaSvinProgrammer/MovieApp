package com.example.room.internal.di

import android.content.Context
import androidx.room.Room
import com.example.room.external.HistoryService
import com.example.room.internal.history.HistoryDao
import com.example.room.internal.AppDatabase
import com.example.room.internal.history.HistoryServiceImpl
import com.example.utils.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface RoomModuleImpl {
    @Binds
    @ApplicationScope
    fun bindsHistoryServiceImpl(service: HistoryServiceImpl): HistoryService

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
