package com.mordva.room.internal.entities.will_watch_package

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
internal interface WillWatchPackageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: WillWatchPackageEntity)

    @Query("DELETE FROM will_watch_package WHERE movie_id = :movieId")
    suspend fun delete(movieId: Int)

    @Transaction
    @Query("SELECT * FROM will_watch_package ORDER BY date DESC")
    fun getSortByDateDesc(): Flow<List<WillWatchPackageDetails>>

    @Transaction
    @Query("SELECT * FROM will_watch_package ORDER BY date ASC")
    fun getSortByDateAsc(): Flow<List<WillWatchPackageDetails>>

    @Query("SELECT * FROM will_watch_package WHERE movie_id = :movieId")
    fun isStock(movieId: Int): Flow<WillWatchPackageEntity?>

    @Query("SELECT COUNT(*) FROM will_watch_package")
    fun count(): Flow<Int>
}
