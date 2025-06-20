package com.example.core.data.repositories

import androidx.paging.PagingSource
import com.example.core.data.room.dao.HistoryDao
import com.example.core.data.room.entity.HistoryEntity
import com.example.core.domain.repositories.HistoryRepository
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val dao: HistoryDao
): HistoryRepository {
    override suspend fun insert(historyEntity: HistoryEntity) {
        dao.insert(historyEntity)
    }

    override suspend fun delete(id: Int) {
        dao.delete(id)
    }

    override fun getAll(): PagingSource<Int, HistoryEntity> {
        return dao.getAll()
    }
}