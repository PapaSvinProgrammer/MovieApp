package com.mordva.room.internal.di

import android.content.Context
import androidx.room.Room
import com.mordva.room.external.BlockedService
import com.mordva.room.external.FavoritePackageService
import com.mordva.room.external.WillWatchPackageService
import com.mordva.room.external.HistoryService
import com.mordva.room.external.MovieLocalService
import com.mordva.room.external.RatedMovieService
import com.mordva.room.external.ViewedService
import com.mordva.room.internal.entities.history.HistoryDao
import com.mordva.room.internal.AppDatabase
import com.mordva.room.internal.entities.blocked.BlockedDao
import com.mordva.room.internal.entities.blocked.BlockedServiceImpl
import com.mordva.room.internal.entities.favorite_package.FavoritePackageDao
import com.mordva.room.internal.entities.favorite_package.FavoritePackageServiceImpl
import com.mordva.room.internal.entities.will_watch_package.WillWatchPackageDao
import com.mordva.room.internal.entities.will_watch_package.WillWatchPackageServiceImpl
import com.mordva.room.internal.entities.history.HistoryServiceImpl
import com.mordva.room.internal.entities.movie.MovieDao
import com.mordva.room.internal.entities.movie.MovieLocalServiceImpl
import com.mordva.room.internal.entities.rated.RatedMovieDao
import com.mordva.room.internal.entities.rated.RatedMovieServiceImpl
import com.mordva.room.internal.entities.viewed.ViewedDao
import com.mordva.room.internal.entities.viewed.ViewedServiceImpl
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

    @Binds
    @ApplicationScope
    fun bindsWillWatchPackageServiceImpl(service: WillWatchPackageServiceImpl): WillWatchPackageService

    @Binds
    @ApplicationScope
    fun bindsFavoritePackageServiceImpl(service: FavoritePackageServiceImpl): FavoritePackageService

    @Binds
    @ApplicationScope
    fun bindsBlockedServiceImpl(service: BlockedServiceImpl): BlockedService

    @Binds
    @ApplicationScope
    fun bindsViewedServiceImpl(service: ViewedServiceImpl): ViewedService

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

        @Provides
        @ApplicationScope
        fun providesWillWatchPackageDao(db: AppDatabase): WillWatchPackageDao {
            return db.getWillWatchPackageDao()
        }

        @Provides
        @ApplicationScope
        fun providesFavoritePackageDao(db: AppDatabase): FavoritePackageDao {
            return db.getFavoritePackageDao()
        }

        @Provides
        @ApplicationScope
        fun providesViewedDao(db: AppDatabase): ViewedDao {
            return db.getViewedDao()
        }

        @Provides
        @ApplicationScope
        fun providesBlockedDao(db: AppDatabase): BlockedDao {
            return db.getBlockedDao()
        }
    }
}
