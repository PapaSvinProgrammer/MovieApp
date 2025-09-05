package com.mordva.room.internal.rated

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RatedMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RatedMovieEntity)

    @Query("DELETE FROM rated_movies WHERE movie_id = :movieId")
    suspend fun delete(movieId: Int)

    @Transaction
    @Query("SELECT * FROM rated_movies")
    fun getAll(): Flow<List<RatedMovieDetails>>

    @Transaction
    @Query("SELECT * FROM rated_movies WHERE rating = :rating")
    fun getAllByRating(rating: Int): Flow<List<RatedMovieDetails>>

    @Query("SELECT * FROM rated_movies WHERE movie_id = :movieId")
    fun isStock(movieId: Int): Flow<RatedMovieEntity?>

    @Query("SELECT * FROM rated_movies ORDER BY date DESC")
    fun getSortByDate(): Flow<RatedMovieEntity?>
}
