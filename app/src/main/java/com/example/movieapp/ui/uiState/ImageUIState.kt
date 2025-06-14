package com.example.movieapp.ui.uiState

import com.example.network.module.image.Poster

sealed interface ImageUIState {
    data object Loading: ImageUIState
    data class Success(val data: List<Poster>): ImageUIState
}