package com.mordva.data

import androidx.paging.PagingData
import com.mordva.domain.repository.HistoryRepository
import com.mordva.model.History
import com.mordva.room.external.HistoryService
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
