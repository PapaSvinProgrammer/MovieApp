package com.example.data.external

import androidx.paging.PagingData
import com.example.model.History
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun insert(history: History)
    suspend fun delete(id: Int)
    fun getAll(): Flow<PagingData<History>>
}