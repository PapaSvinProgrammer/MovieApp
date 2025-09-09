package com.mordva.room.internal.entities.blocked

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
internal interface BlockedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: BlockedEntity)

    @Query("DELETE FROM blocked WHERE movie_id = :movieId")
    suspend fun delete(movieId: Int)

    @Transaction
    @Query("SELECT * FROM blocked ORDER BY date DESC")
    fun getSortByDateDesc(): Flow<List<BlockedDetails>>

    @Transaction
    @Query("SELECT * FROM blocked ORDER BY date ASC")
    fun getSortByDateAsc(): Flow<List<BlockedDetails>>

    @Query("SELECT * FROM blocked WHERE movie_id = :movieId")
    fun isStock(movieId: Int): Flow<BlockedEntity?>

    @Query("SELECT COUNT(*) FROM blocked")
    fun count(): Flow<Int>
}