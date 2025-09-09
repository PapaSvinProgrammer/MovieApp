package com.mordva.movieScreen.presentation.movie.widget.moreBottomSheet

internal sealed interface MoreSheetAction {
    data object AddInFolder : MoreSheetAction
    data object VisibilityChange : MoreSheetAction
    data object BlockedChange : MoreSheetAction
}