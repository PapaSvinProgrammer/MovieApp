package com.example.personscreen.presentation.widget

import com.example.model.movie.ShortMovie
import com.example.ui.uiState.FactUIState
import com.example.ui.uiState.MovieUIState
import com.example.ui.uiState.PersonUIState

internal data class UiState(
    val personState: PersonUIState = PersonUIState.Loading,
    val moviesState: MovieUIState = MovieUIState.Loading,
    val factState: FactUIState = FactUIState.Loading,
    val countAwards: Int? = null,
    val personSpouseState: PersonUIState = PersonUIState.Loading,
    val groups: Map<String, List<ShortMovie>> = mapOf(),
    val groupsKeys: List<String> = listOf(),
    val selectedGroup: Int = 0,
    val moviesFromGroup: List<ShortMovie> = listOf(),
)