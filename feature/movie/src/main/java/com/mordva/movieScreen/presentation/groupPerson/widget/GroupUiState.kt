package com.mordva.movieScreen.presentation.groupPerson.widget

import com.mordva.movieScreen.domain.model.PersonMovieExtended

internal sealed interface GroupUiState {
    data class Success(val data: Map<String, List<PersonMovieExtended>>) : GroupUiState
    data object Loading : GroupUiState
}

