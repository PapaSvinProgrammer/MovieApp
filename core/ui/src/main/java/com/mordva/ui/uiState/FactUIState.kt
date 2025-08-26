package com.mordva.ui.uiState

import androidx.compose.runtime.Immutable
import com.mordva.model.movie.Fact

@Immutable
sealed interface FactUIState {
    data object Loading: FactUIState
    data class Success(val data: List<Fact>): FactUIState
}