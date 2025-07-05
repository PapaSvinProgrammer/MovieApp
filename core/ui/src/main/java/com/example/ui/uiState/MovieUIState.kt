package com.example.ui.uiState

import com.example.model.movie.Movie

sealed interface MovieUIState {
    data class Success(val data: List<Movie>): MovieUIState
    data object Loading: MovieUIState
}