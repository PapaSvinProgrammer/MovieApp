package com.mordva.ui.uiState

import androidx.compose.runtime.Immutable
import com.mordva.model.movie.Comment

@Immutable
sealed interface CommentUIState {
    data class Success(val data: List<Comment>): CommentUIState
    data object Loading: CommentUIState
}