package com.example.core.data.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.room.entity.HistoryEntity

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("SELECT * FROM search_history ORDER BY id DESC")
    fun getAll(): PagingSource<Int, HistoryEntity>

    @Query("DELETE FROM search_history WHERE movie_id = :id")
    suspend fun delete(id: Int)
}