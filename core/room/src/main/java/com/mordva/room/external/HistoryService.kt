package com.mordva.room.external

import androidx.paging.PagingData
import com.mordva.model.local.History
import kotlinx.coroutines.flow.Flow

interface HistoryService {
    suspend fun insert(history: History): Result<Unit>
    fun getAll(): Flow<PagingData<History>>
    suspend fun delete(id: Int): Result<Unit>
}