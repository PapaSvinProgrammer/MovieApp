package com.mordva.movieScreen.presentation.movie.widget

import com.mordva.model.image.CollectionMovie
import com.mordva.model.image.Poster
import com.mordva.model.local.RatedMovie
import com.mordva.model.movie.Comment
import com.mordva.model.person.PersonMovie
import com.mordva.ui.uiState.MovieUIState
import com.mordva.movieScreen.presentation.movie.widget.scoreBottomSheet.RatedMovieState

internal data class UIState(
    val movieState: MovieUIState = MovieUIState.Loading,
    val actors: List<PersonMovie> = listOf(),
    val voiceActors: List<PersonMovie> = listOf(),
    val supportPersonal: List<PersonMovie> = listOf(),
    val comments: List<Comment> = listOf(),
    val images: List<Poster> = listOf(),
    val collections: List<CollectionMovie> = listOf(),
    val ratedMoviesState: RatedMovieState = RatedMovieState.Init,
    val isRatedMovieState: RatedMovie? = null,
    val currentMovieRating: Int = -1,
    val selectedFact: String = "",
    val isCollapsed: Boolean = false,
    val scoreSheetVisible: Boolean = false
)