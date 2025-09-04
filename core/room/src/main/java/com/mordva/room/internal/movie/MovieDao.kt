package com.mordva.room.internal.movie

import androidx.room.*
import com.mordva.room.internal.movie.entity.AudienceEntity
import com.mordva.room.internal.movie.entity.BudgetEntity
import com.mordva.room.internal.movie.entity.CountryEntity
import com.mordva.room.internal.movie.entity.DistributorEntity
import com.mordva.room.internal.movie.entity.EpisodeEntity
import com.mordva.room.internal.movie.entity.FactEntity
import com.mordva.room.internal.movie.entity.GenreEntity
import com.mordva.room.internal.movie.entity.MovieEntity
import com.mordva.room.internal.movie.entity.PersonMovieEntity
import com.mordva.room.internal.movie.entity.PosterEntity
import com.mordva.room.internal.movie.entity.PremiereEntity
import com.mordva.room.internal.movie.entity.RatingEntity
import com.mordva.room.internal.movie.entity.ReleaseYearsEntity
import com.mordva.room.internal.movie.entity.SeasonEntity
import com.mordva.room.internal.movie.entity.VotesEntity
import com.mordva.room.internal.movie.entity.WatchabilityItemEntity

@Dao
internal interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRatings(ratings: List<RatingEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVotes(votes: List<VotesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDistributors(distributors: List<DistributorEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPremieres(premieres: List<PremiereEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudgets(budgets: List<BudgetEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosters(posters: List<PosterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFacts(facts: List<FactEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersons(persons: List<PersonMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchabilityItems(items: List<WatchabilityItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAudience(audience: List<AudienceEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReleaseYears(releaseYears: List<ReleaseYearsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeasons(seasons: List<SeasonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodes: List<EpisodeEntity>)

    @Query("DELETE FROM movie WHERE id = :movieId")
    suspend fun deleteMovie(movieId: Int)

    @Query("DELETE FROM rating WHERE movie_id = :movieId")
    suspend fun deleteRatings(movieId: Int)

    @Query("DELETE FROM votes WHERE movie_id = :movieId")
    suspend fun deleteVotes(movieId: Int)

    @Query("DELETE FROM distributor WHERE movie_id = :movieId")
    suspend fun deleteDistributors(movieId: Int)

    @Query("DELETE FROM premiere WHERE movie_id = :movieId")
    suspend fun deletePremieres(movieId: Int)

    @Query("DELETE FROM budgets WHERE movie_id = :movieId")
    suspend fun deleteBudgets(movieId: Int)

    @Query("DELETE FROM poster WHERE movie_id = :movieId")
    suspend fun deletePosters(movieId: Int)

    @Query("DELETE FROM fact WHERE movie_id = :movieId")
    suspend fun deleteFacts(movieId: Int)

    @Query("DELETE FROM genres WHERE movie_id = :movieId")
    suspend fun deleteGenres(movieId: Int)

    @Query("DELETE FROM country WHERE movie_id = :movieId")
    suspend fun deleteCountries(movieId: Int)

    @Query("DELETE FROM person_movie WHERE movie_id = :movieId")
    suspend fun deletePersons(movieId: Int)

    @Query("DELETE FROM watchability_item WHERE movie_id = :movieId")
    suspend fun deleteWatchabilityItems(movieId: Int)

    @Query("DELETE FROM audience WHERE movie_id = :movieId")
    suspend fun deleteAudience(movieId: Int)

    @Query("DELETE FROM release_years WHERE movie_id = :movieId")
    suspend fun deleteReleaseYears(movieId: Int)

    @Query("DELETE FROM season WHERE movie_id = :movieId")
    suspend fun deleteSeasons(movieId: Int)

    @Transaction
    @Query("SELECT * FROM movie WHERE id = :movieId")
    suspend fun getMovieWithDetails(movieId: Int): MovieDetails?

    @Transaction
    @Query("SELECT * FROM movie")
    suspend fun getAllMoviesWithDetails(): List<MovieDetails>

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Update
    suspend fun updateSeasons(seasons: List<SeasonEntity>)

    @Update
    suspend fun updateEpisodes(episodes: List<EpisodeEntity>)
}
