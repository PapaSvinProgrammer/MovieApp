package com.example.core.domain.searchHistory

import com.example.core.data.repositories.HistoryRepository
import com.example.core.data.repositoriesImpl.MovieRepositoryImpl
import javax.inject.Inject

class DeleteSearchHistory @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    suspend fun execute(id: Int) {
        if (id < 0) return

        historyRepository.delete(id)
    }
}