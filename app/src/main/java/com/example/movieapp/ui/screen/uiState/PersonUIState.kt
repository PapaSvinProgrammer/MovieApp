package com.example.movieapp.ui.screen.uiState

import com.example.network.module.person.Person

sealed interface PersonUIState {
    data class Success(val data: List<Person>): PersonUIState
    data object Loading:PersonUIState
}