package com.mordva.ui.widget.scoreBottomSheet

import com.mordva.model.local.RatedMovie

sealed interface RatedMovieState {
    data object Init : RatedMovieState
    data class Success(val data: List<RatedMovie>) : RatedMovieState
    data object Loading : RatedMovieState
    data class Error(val error: Throwable) : RatedMovieState
}