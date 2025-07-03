package com.example.data.internal

import androidx.paging.PagingSource
import com.example.data.external.HistoryRepository
import com.example.model.History
import com.example.room.external.HistoryDao
import com.example.room.external.toEntity
import com.example.room.internal.HistoryEntity
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val dao: HistoryDao
) : HistoryRepository {
    override suspend fun insert(history: History) {
        dao.insert(history.toEntity())
    }

    override suspend fun delete(id: Int) {
        dao.delete(id)
    }

    override fun getAll(): PagingSource<Int, HistoryEntity> {
        return dao.getAll()
    }
}