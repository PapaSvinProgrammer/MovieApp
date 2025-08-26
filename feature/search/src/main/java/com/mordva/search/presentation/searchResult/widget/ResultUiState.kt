package com.mordva.search.presentation.searchResult.widget

import com.mordva.ui.uiState.MovieUIState

internal data class ResultUiState(
    val page: Int = 1,
    val movieState: MovieUIState = MovieUIState.Loading
)