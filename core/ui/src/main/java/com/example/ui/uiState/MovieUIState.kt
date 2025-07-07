package com.example.ui.uiState

import androidx.compose.runtime.Immutable
import com.example.model.movie.Movie

@Immutable
sealed interface MovieUIState {
    data class Success(val data: List<Movie>): MovieUIState
    data object Loading: MovieUIState
}