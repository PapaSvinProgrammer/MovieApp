package com.example.movieapp.ui.uiState

import com.example.network.module.movie.Movie

sealed interface MovieUIState {
    data class Success(val data: List<Movie>): MovieUIState
    data object Loading: MovieUIState
}