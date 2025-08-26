package com.mordva.room.internal.di

import android.content.Context
import androidx.room.Room
import com.mordva.room.external.HistoryService
import com.mordva.room.internal.history.HistoryDao
import com.mordva.room.internal.AppDatabase
import com.mordva.room.internal.history.HistoryServiceImpl
import com.mordva.util.ApplicationScope
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
