package com.example.searchhistory

import com.example.data.external.HistoryRepository
import javax.inject.Inject

class DeleteSearchHistory @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    suspend fun execute(id: Int) {
        if (id < 0) return

        historyRepository.delete(id)
    }
}