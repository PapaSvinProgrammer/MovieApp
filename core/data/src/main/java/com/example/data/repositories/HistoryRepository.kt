package com.example.data.repositories

import androidx.paging.PagingSource
import com.example.core.data.room.entity.HistoryEntity

interface HistoryRepository {
    suspend fun insert(historyEntity: HistoryEntity)
    suspend fun delete(id: Int)
    fun getAll(): PagingSource<Int, HistoryEntity>
}