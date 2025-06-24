package com.example.movieapp.ui.uiState

import com.example.core.domain.model.SearchItem

interface SearchUIState {
    data class Success(val data: List<SearchItem>): SearchUIState
    data object Loading: SearchUIState
    data object Error: SearchUIState
}