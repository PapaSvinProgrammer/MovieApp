package com.example.ui.uiState

import androidx.compose.runtime.Immutable
import com.example.model.image.CollectionMovie

@Immutable
sealed interface CollectionUIState {
    data class Success(val data: List<CollectionMovie>): CollectionUIState
    data object Loading: CollectionUIState
}