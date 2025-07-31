package com.example.search.searchScreen.widget

import com.example.ui.uiState.CollectionUIState
import com.example.ui.uiState.MovieUIState
import com.example.ui.uiState.PersonUIState
import com.example.ui.uiState.SearchUIState

internal data class UiState(
    val query: String = "",
    val isExpanded: Boolean = false,
    val collectionsState: CollectionUIState = CollectionUIState.Loading,
    val personState: PersonUIState = PersonUIState.Loading,
    val topSerialsState: MovieUIState = MovieUIState.Loading,
    val selectedSearchIndex: Int = 0,
    val searchState: SearchUIState = SearchUIState.Error
)