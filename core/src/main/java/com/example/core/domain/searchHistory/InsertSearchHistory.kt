package com.example.core.domain.searchHistory

import com.example.core.data.repositories.HistoryRepository
import com.example.core.data.room.entity.HistoryEntity
import javax.inject.Inject

class InsertSearchHistory @Inject constructor(
    private val searchHistoryRepository: HistoryRepository
) {
    suspend fun execute(historyEntity: HistoryEntity) {
        searchHistoryRepository.insert(historyEntity)
    }
}