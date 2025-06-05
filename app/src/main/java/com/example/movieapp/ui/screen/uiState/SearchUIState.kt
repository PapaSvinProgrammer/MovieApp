package com.example.movieapp.ui.screen.uiState

import com.example.core.domain.module.SearchItem

interface SearchUIState {
    data class Success(val data: List<SearchItem>): SearchUIState
    data object Loading: SearchUIState
    data object Error: SearchUIState
}