package com.example.movieapp.ui.uiState

import com.example.network.module.movie.Fact

sealed interface FactUIState {
    data object Loading: FactUIState
    data class Success(val data: List<Fact>): FactUIState
}