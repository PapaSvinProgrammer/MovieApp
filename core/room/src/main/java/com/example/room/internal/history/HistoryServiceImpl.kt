package com.example.room.internal.history

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.model.History
import com.example.room.external.HistoryService
import com.example.room.internal.utils.safeCall
import com.example.room.internal.utils.toDomain
import com.example.room.internal.utils.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class HistoryServiceImpl @Inject constructor(
    private val dao: HistoryDao
) : HistoryService {
    override suspend fun insert(history: History): Result<Unit> {
        return safeCall {
            dao.insert(history.toEntity())
        }
    }

    override fun getAll(): Flow<PagingData<History>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 20
            ),
            pagingSourceFactory = { dao.getAll() }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }
    }

    override suspend fun delete(id: Int): Result<Unit> {
        return safeCall {
            dao.delete(id)
        }
    }
}
