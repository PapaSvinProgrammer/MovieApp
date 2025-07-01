package com.example.ui.uiState

import com.example.model.image.Poster

sealed interface ImageUIState {
    data object Loading: ImageUIState
    data class Success(val data: List<Poster>): ImageUIState
}