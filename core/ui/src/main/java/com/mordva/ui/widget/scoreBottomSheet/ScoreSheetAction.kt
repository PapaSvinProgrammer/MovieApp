package com.mordva.ui.widget.scoreBottomSheet

sealed interface ScoreSheetAction {
    data class ValueChange(val rating: Int) : ScoreSheetAction
    data class Save(val rating: Int) : ScoreSheetAction
    data object Nothing : ScoreSheetAction
    data object Delete : ScoreSheetAction
}