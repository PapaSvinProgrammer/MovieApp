package com.mordva.room.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mordva.room.internal.history.HistoryDao
import com.mordva.room.internal.history.HistoryEntity
import com.mordva.room.internal.movie.MovieDao
import com.mordva.room.internal.movie.entity.AudienceEntity
import com.mordva.room.internal.movie.entity.BudgetEntity
import com.mordva.room.internal.movie.entity.CountryEntity
import com.mordva.room.internal.movie.entity.DistributorEntity
import com.mordva.room.internal.movie.entity.EpisodeEntity
import com.mordva.room.internal.movie.entity.FactEntity
import com.mordva.room.internal.movie.entity.GenreEntity
import com.mordva.room.internal.movie.entity.MovieEntity
import com.mordva.room.internal.movie.entity.PersonEntity
import com.mordva.room.internal.movie.entity.PosterEntity
import com.mordva.room.internal.movie.entity.PremiereEntity
import com.mordva.room.internal.movie.entity.RatingEntity
import com.mordva.room.internal.movie.entity.ReleaseYearsEntity
import com.mordva.room.internal.movie.entity.SeasonEntity
import com.mordva.room.internal.movie.entity.VotesEntity
import com.mordva.room.internal.movie.entity.WatchabilityItemEntity
import com.mordva.room.internal.rated.RatedMovieDao
import com.mordva.room.internal.rated.RatedMovieEntity

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
        PersonEntity::class,
        WatchabilityItemEntity::class,
        AudienceEntity::class,
        ReleaseYearsEntity::class,
        SeasonEntity::class,
        EpisodeEntity::class,
    ],
    version = 1,
    exportSchema = true
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao
    abstract fun getRatedDao(): RatedMovieDao
    abstract fun getMovieDao(): MovieDao
}