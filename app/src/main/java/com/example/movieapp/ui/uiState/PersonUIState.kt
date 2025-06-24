package com.example.movieapp.ui.uiState

import com.example.network.model.person.Person

sealed interface PersonUIState {
    data class Success(val data: List<Person>): PersonUIState
    data object Loading: PersonUIState
}