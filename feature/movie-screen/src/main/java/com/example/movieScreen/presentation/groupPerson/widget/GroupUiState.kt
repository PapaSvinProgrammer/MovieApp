package com.example.movieScreen.presentation.groupPerson.widget

import com.example.movieScreen.domain.model.PersonMovieExtended

internal sealed interface GroupUiState {
    data class Success(val data: Map<String, List<PersonMovieExtended>>) : GroupUiState
    data object Loading : GroupUiState
}

