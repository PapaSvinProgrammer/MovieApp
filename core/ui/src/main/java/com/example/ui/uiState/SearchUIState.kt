package com.example.ui.uiState

import androidx.compose.runtime.Immutable
import com.example.model.SearchItem

@Immutable
interface SearchUIState {
    data class Success(val data: List<SearchItem>): SearchUIState
    data object Loading: SearchUIState
    data object Error: SearchUIState
}