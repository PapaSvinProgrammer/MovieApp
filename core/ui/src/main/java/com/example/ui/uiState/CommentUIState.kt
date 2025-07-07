package com.example.ui.uiState

import androidx.compose.runtime.Immutable
import com.example.model.movie.Comment

@Immutable
sealed interface CommentUIState {
    data class Success(val data: List<Comment>): CommentUIState
    data object Loading: CommentUIState
}