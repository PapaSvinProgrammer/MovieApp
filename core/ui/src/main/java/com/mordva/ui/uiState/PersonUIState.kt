package com.mordva.ui.uiState

import androidx.compose.runtime.Immutable
import com.mordva.model.person.Person

@Immutable
sealed interface PersonUIState {
    data class Success(val data: List<Person>): PersonUIState
    data object Loading: PersonUIState
}