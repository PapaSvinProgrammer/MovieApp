package com.mordva.ui.uiState

import androidx.compose.runtime.Immutable
import com.mordva.model.movie.Movie

@Immutable
sealed interface MovieUIState {
    data class Success(val data: List<Movie>): MovieUIState
    data object Loading: MovieUIState
}