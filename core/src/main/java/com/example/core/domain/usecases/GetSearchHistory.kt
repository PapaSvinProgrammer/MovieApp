package com.example.core.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.room.entity.HistoryEntity
import com.example.core.domain.repositories.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchHistory @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    fun execute(): Flow<PagingData<HistoryEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 20
            ),
            pagingSourceFactory = { historyRepository.getAll() }
        ).flow
    }
}