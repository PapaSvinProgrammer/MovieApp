package com.mordva.movieScreen.presentation.movie.widget.scoreBottomSheet

internal sealed interface ScoreSheetAction {
    data class ValueChange(val rating: Int) : ScoreSheetAction
    data class Save(val rating: Int) : ScoreSheetAction
    data object Nothing : ScoreSheetAction
    data object Delete : ScoreSheetAction
}