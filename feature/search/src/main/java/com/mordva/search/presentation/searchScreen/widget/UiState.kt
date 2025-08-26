package com.mordva.search.presentation.searchScreen.widget

import com.mordva.ui.uiState.CollectionUIState
import com.mordva.ui.uiState.MovieUIState
import com.mordva.ui.uiState.PersonUIState
import com.mordva.ui.uiState.SearchUIState

internal data class UiState(
    val query: String = "",
    val isExpanded: Boolean = false,
    val collectionsState: CollectionUIState = CollectionUIState.Loading,
    val personState: PersonUIState = PersonUIState.Loading,
    val topSerialsState: MovieUIState = MovieUIState.Loading,
    val selectedSearchIndex: Int = 0,
    val searchState: SearchUIState = SearchUIState.Error,
    val page: Int = 1
)