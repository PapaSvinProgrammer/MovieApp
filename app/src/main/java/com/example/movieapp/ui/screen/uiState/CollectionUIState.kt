package com.example.movieapp.ui.screen.uiState

import com.example.network.module.image.Collection

sealed interface CollectionUIState {
    data class Success(val data: List<Collection>): CollectionUIState
    data class Failure(val message: String): CollectionUIState
    data object Loading: CollectionUIState
}