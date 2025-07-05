package com.example.searchhistory

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.external.HistoryRepository
import com.example.model.History
import com.example.room.external.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSearchHistory @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    fun execute(): Flow<PagingData<History>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 20
            ),
            pagingSourceFactory = { historyRepository.getAll() }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }
    }
}