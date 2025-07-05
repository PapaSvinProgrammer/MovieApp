package com.example.ui.uiState

import com.example.model.person.Person

sealed interface PersonUIState {
    data class Success(val data: List<Person>): PersonUIState
    data object Loading: PersonUIState
}