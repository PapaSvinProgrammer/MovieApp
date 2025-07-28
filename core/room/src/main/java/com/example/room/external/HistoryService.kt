package com.example.room.external

import androidx.paging.PagingData
import com.example.model.History
import kotlinx.coroutines.flow.Flow

interface HistoryService {
    suspend fun insert(history: History): Result<Unit>
    fun getAll(): Flow<PagingData<History>>
    suspend fun delete(id: Int): Result<Unit>
}