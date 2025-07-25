package com.example.data.internal

import androidx.paging.PagingData
import com.example.data.external.HistoryRepository
import com.example.model.History
import com.example.room.external.HistoryService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class HistoryRepositoryImpl @Inject constructor(
    private val service: HistoryService
) : HistoryRepository {
    override suspend fun insert(history: History) {
        service.insert(history)
    }

    override suspend fun delete(id: Int) {
        service.delete(id)
    }

    override fun getAll(): Flow<PagingData<History>> {
        return service.getAll()
    }
}
