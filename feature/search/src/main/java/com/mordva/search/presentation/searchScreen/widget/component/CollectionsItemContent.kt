package com.mordva.search.presentation.searchScreen.widget.component

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.movieapp.ui.R
import com.mordva.navigation.CollectionListGraph
import com.mordva.search.presentation.searchScreen.util.navigateToMovieList
import com.mordva.ui.uiState.CollectionUIState
import com.mordva.ui.widget.renderState.RenderCollectionStateRow

internal fun LazyListScope.collectionsItemContent(
    state: CollectionUIState,
    navController: NavController,
    get: () -> Unit
) {
    item {
        get()

        RenderCollectionStateRow(
            state = state,
            title = stringResource(R.string.advise_watch),
            onClick = { navigateToMovieList(navController, it) },
            onShowAll = {
                navController.navigate(
                    CollectionListGraph.CollectionListRoute("Фильмы")
                )
            }
        )
    }
}