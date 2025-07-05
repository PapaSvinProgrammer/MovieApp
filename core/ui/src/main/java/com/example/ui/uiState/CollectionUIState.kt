package com.example.ui.uiState

import com.example.model.image.CollectionMovie

sealed interface CollectionUIState {
    data class Success(val data: List<CollectionMovie>): CollectionUIState
    data object Loading: CollectionUIState
}