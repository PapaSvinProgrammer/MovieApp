package com.mordva.room.internal.rated

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RatedMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RatedMovieEntity)

    @Query("DELETE FROM rated_movies WHERE movie_id = :movieId")
    suspend fun delete(movieId: Int)

    @Query("SELECT * FROM rated_movies")
    fun getAll(): Flow<List<RatedMovieWithDetails>>

    @Query("SELECT * FROM rated_movies WHERE rating = :rating")
    fun getAllByRating(rating: Int): Flow<List<RatedMovieWithDetails>>

    @Query("SELECT * FROM rated_movies WHERE movie_id = :movieId")
    fun getById(movieId: Int): Flow<RatedMovieWithDetails?>

    @Query("SELECT * FROM rated_movies ORDER BY date DESC")
    fun getSortByDate(): Flow<RatedMovieEntity?>
}
