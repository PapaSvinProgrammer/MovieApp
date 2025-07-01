package com.example.ui.uiState

import com.example.model.image.Collection

sealed interface CollectionUIState {
    data class Success(val data: List<Collection>): CollectionUIState
    data object Loading: CollectionUIState
}