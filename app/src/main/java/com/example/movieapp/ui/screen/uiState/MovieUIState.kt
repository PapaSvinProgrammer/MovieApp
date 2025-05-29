package com.example.movieapp.ui.screen.uiState

import com.example.network.module.movie.Movie

sealed interface MovieUIState {
    data class Success(val data: List<Movie>): MovieUIState
    data class Failure(val message: String): MovieUIState
    data object Loading: MovieUIState
}