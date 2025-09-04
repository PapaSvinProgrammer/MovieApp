package com.mordva.movieScreen.domain.model

import com.mordva.model.movie.Movie
import com.mordva.movieScreen.presentation.movie.widget.scoreBottomSheet.ScoreSheetAction

internal data class RatedMovieActionParams(
    val scoreSheetAction: ScoreSheetAction,
    val currentScore: Int,
    val movie: Movie
)