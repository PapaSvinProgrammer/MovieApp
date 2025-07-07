package com.example.ui.uiState

import androidx.compose.runtime.Immutable
import com.example.model.image.Poster

@Immutable
sealed interface ImageUIState {
    data object Loading: ImageUIState
    data class Success(val data: List<Poster>): ImageUIState
}