package com.example.searchhistory

import com.example.data.external.HistoryRepository
import com.example.model.History
import javax.inject.Inject

class InsertSearchHistory @Inject constructor(
    private val searchHistoryRepository: HistoryRepository
) {
    suspend fun execute(history: History) {
        searchHistoryRepository.insert(history)
    }
}