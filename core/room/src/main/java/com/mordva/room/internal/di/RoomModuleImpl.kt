package com.mordva.room.internal.di

import android.content.Context
import androidx.room.Room
import com.mordva.room.external.HistoryService
import com.mordva.room.external.MovieLocalService
import com.mordva.room.external.RatedMovieService
import com.mordva.room.internal.history.HistoryDao
import com.mordva.room.internal.AppDatabase
import com.mordva.room.internal.history.HistoryServiceImpl
import com.mordva.room.internal.movie.MovieDao
import com.mordva.room.internal.movie.MovieLocalServiceImpl
import com.mordva.room.internal.rated.RatedMovieDao
import com.mordva.room.internal.rated.RatedMovieServiceImpl
import com.mordva.util.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface RoomModuleImpl {
    @Binds
    @ApplicationScope
    fun bindsHistoryServiceImpl(service: HistoryServiceImpl): HistoryService

    @Binds
    @ApplicationScope
    fun bindsRatedMovieServiceImpl(service: RatedMovieServiceImpl): RatedMovieService

    @Binds
    @ApplicationScope
    fun bindsMovieLocalServiceImpl(service: MovieLocalServiceImpl): MovieLocalService

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

        @Provides
        @ApplicationScope
        fun providesRatedMovieDao(db: AppDatabase): RatedMovieDao {
            return db.getRatedDao()
        }

        @Provides
        @ApplicationScope
        fun providesMovieDao(db: AppDatabase): MovieDao {
            return db.getMovieDao()
        }
    }
}
