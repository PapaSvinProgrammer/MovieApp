package com.example.ui.uiState

import com.example.model.SearchItem

interface SearchUIState {
    data class Success(val data: List<SearchItem>): SearchUIState

    data object Loading: SearchUIState

    data object Error: SearchUIState
}