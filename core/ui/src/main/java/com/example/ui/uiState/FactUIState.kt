package com.example.ui.uiState

import com.example.model.movie.Fact

sealed interface FactUIState {
    data object Loading: FactUIState
    data class Success(val data: List<Fact>): FactUIState
}