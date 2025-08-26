package com.mordva.room.internal.history

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
internal interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("SELECT * FROM search_history ORDER BY id DESC")
    fun getAll(): PagingSource<Int, HistoryEntity>

    @Query("DELETE FROM search_history WHERE movie_id = :id")
    suspend fun delete(id: Int)
}
