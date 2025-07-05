package com.example.data.external

import androidx.paging.PagingSource
import com.example.model.History
import com.example.room.internal.HistoryEntity

interface HistoryRepository {
    suspend fun insert(history: History)

    suspend fun delete(id: Int)

    fun getAll(): PagingSource<Int, HistoryEntity>
}