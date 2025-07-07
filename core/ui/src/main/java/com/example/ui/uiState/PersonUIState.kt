package com.example.ui.uiState

import androidx.compose.runtime.Immutable
import com.example.model.person.Person

@Immutable
sealed interface PersonUIState {
    data class Success(val data: List<Person>): PersonUIState
    data object Loading: PersonUIState
}