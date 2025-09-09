package com.mordva.room.internal.entities.viewed

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ViewedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ViewedEntity)

    @Query("DELETE FROM viewed WHERE movie_id = :movieId")
    suspend fun delete(movieId: Int)

    @Transaction
    @Query("SELECT * FROM viewed ORDER BY date DESC")
    fun getSortByDateDesc(): Flow<List<ViewedDetails>>

    @Transaction
    @Query("SELECT * FROM viewed ORDER BY date ASC")
    fun getSortByDateAsc(): Flow<List<ViewedDetails>>

    @Query("SELECT * FROM viewed WHERE movie_id = :movieId")
    fun isStock(movieId: Int): Flow<ViewedEntity?>

    @Query("SELECT COUNT(*) FROM will_watch_package")
    fun count(): Flow<Int>
}