package com.mordva.room.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mordva.room.internal.entities.blocked.BlockedDao
import com.mordva.room.internal.entities.blocked.BlockedEntity
import com.mordva.room.internal.entities.history.HistoryEntity
import com.mordva.room.internal.entities.favorite_package.FavoritePackageDao
import com.mordva.room.internal.entities.favorite_package.FavoritePackageEntity
import com.mordva.room.internal.entities.history.HistoryDao
import com.mordva.room.internal.entities.movie.MovieDao
import com.mordva.room.internal.entities.movie.entity.AudienceEntity
import com.mordva.room.internal.entities.movie.entity.BudgetEntity
import com.mordva.room.internal.entities.movie.entity.CountryEntity
import com.mordva.room.internal.entities.movie.entity.DistributorEntity
import com.mordva.room.internal.entities.movie.entity.EpisodeEntity
import com.mordva.room.internal.entities.movie.entity.FactEntity
import com.mordva.room.internal.entities.movie.entity.GenreEntity
import com.mordva.room.internal.entities.movie.entity.MovieEntity
import com.mordva.room.internal.entities.movie.entity.PersonMovieEntity
import com.mordva.room.internal.entities.movie.entity.PosterEntity
import com.mordva.room.internal.entities.movie.entity.PremiereEntity
import com.mordva.room.internal.entities.movie.entity.RatingEntity
import com.mordva.room.internal.entities.movie.entity.ReleaseYearsEntity
import com.mordva.room.internal.entities.movie.entity.SeasonEntity
import com.mordva.room.internal.entities.movie.entity.VotesEntity
import com.mordva.room.internal.entities.movie.entity.WatchabilityItemEntity
import com.mordva.room.internal.entities.rated.RatedMovieDao
import com.mordva.room.internal.entities.rated.RatedMovieEntity
import com.mordva.room.internal.entities.viewed.ViewedDao
import com.mordva.room.internal.entities.viewed.ViewedEntity
import com.mordva.room.internal.entities.will_watch_package.WillWatchPackageDao
import com.mordva.room.internal.entities.will_watch_package.WillWatchPackageEntity

@Database(
    entities = [
        HistoryEntity::class,
        RatedMovieEntity::class,
        MovieEntity::class,
        RatingEntity::class,
        VotesEntity::class,
        DistributorEntity::class,
        PremiereEntity::class,
        BudgetEntity::class,
        PosterEntity::class,
        FactEntity::class,
        GenreEntity::class,
        CountryEntity::class,
        PersonMovieEntity::class,
        WatchabilityItemEntity::class,
        AudienceEntity::class,
        ReleaseYearsEntity::class,
        SeasonEntity::class,
        EpisodeEntity::class,
        WillWatchPackageEntity::class,
        FavoritePackageEntity::class,
        ViewedEntity::class,
        BlockedEntity::class,
    ],
    version = 1,
    exportSchema = true
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao
    abstract fun getRatedDao(): RatedMovieDao
    abstract fun getMovieDao(): MovieDao
    abstract fun getWillWatchPackageDao(): WillWatchPackageDao
    abstract fun getFavoritePackageDao(): FavoritePackageDao
    abstract fun getViewedDao(): ViewedDao
    abstract fun getBlockedDao(): BlockedDao
}