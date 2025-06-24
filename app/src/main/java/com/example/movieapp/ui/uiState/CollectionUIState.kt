package com.example.movieapp.ui.uiState

import com.example.network.model.image.Collection

sealed interface CollectionUIState {
    data class Success(val data: List<Collection>): CollectionUIState
    data object Loading: CollectionUIState
}