package com.example.movieScreen.presentation.movie.widget

import com.example.model.image.CollectionMovie
import com.example.model.image.Poster
import com.example.model.movie.Comment
import com.example.model.person.PersonMovie
import com.example.ui.uiState.MovieUIState

internal data class UIState(
    val movieState: MovieUIState = MovieUIState.Loading,
    val actors: List<PersonMovie> = listOf(),
    val voiceActors: List<PersonMovie> = listOf(),
    val supportPersonal: List<PersonMovie> = listOf(),
    val comments: List<Comment> = listOf(),
    val images: List<Poster> = listOf(),
    val collections: List<CollectionMovie> = listOf(),
    val selectedFact: String = ""
)