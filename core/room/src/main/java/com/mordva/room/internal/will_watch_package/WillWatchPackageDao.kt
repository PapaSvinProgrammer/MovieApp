package com.mordva.room.internal.will_watch_package

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface WillWatchPackageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: WillWatchPackageEntity)

    @Query("DELETE FROM favorite_movie_package WHERE movie_id = :movieId")
    suspend fun delete(movieId: Int)

    @Query("SELECT * FROM favorite_movie_package ORDER BY date DESC")
    fun getSortByDateDesc(): Flow<List<WillWatchPackageDetails>>

    @Query("SELECT * FROM favorite_movie_package ORDER BY date ASC")
    fun getSortByDateAsc(): Flow<List<WillWatchPackageDetails>>

    @Query("SELECT * FROM favorite_movie_package WHERE movie_id = :movieId")
    fun isStock(movieId: Int): Flow<WillWatchPackageEntity?>
}
