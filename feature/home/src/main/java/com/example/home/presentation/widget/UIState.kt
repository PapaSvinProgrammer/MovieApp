package com.example.home.presentation.widget

import com.example.ui.uiState.CollectionUIState
import com.example.ui.uiState.MovieUIState

internal data class UIState(
    val movieDramaState: MovieUIState = MovieUIState.Loading,
    val movieBoevikState: MovieUIState = MovieUIState.Loading,
    val movieBest250State: MovieUIState = MovieUIState.Loading,
    val movieBest501State: MovieUIState = MovieUIState.Loading,
    val movieBest100State: MovieUIState = MovieUIState.Loading,
    val movieHBOState: MovieUIState = MovieUIState.Loading,
    val movieNetflixState: MovieUIState = MovieUIState.Loading,
    val collectionState: CollectionUIState = CollectionUIState.Loading,
)