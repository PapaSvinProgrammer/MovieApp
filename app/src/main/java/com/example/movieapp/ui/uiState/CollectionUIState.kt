package com.example.movieapp.ui.uiState

import com.example.network.module.image.Collection

sealed interface CollectionUIState {
    data class Success(val data: List<Collection>): CollectionUIState
    data object Loading: CollectionUIState
}