package com.mordva.search.presentation.searchScreen.widget.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.ui.R
import com.mordva.navigation.MovieGraph
import com.mordva.navigation.MovieListGraph
import com.mordva.ui.uiState.MovieUIState
import com.mordva.ui.widget.renderState.RenderMovieStateRow
import com.mordva.util.Constants

internal fun LazyListScope.serialsItemContent(
    state: MovieUIState,
    navController: NavController,
    get: () -> Unit
) {
    item {
        val title = stringResource(R.string.popular_serials)
        get()

        RenderMovieStateRow(
            state = state,
            title = title,
            onClick = {
                navController.navigate(MovieGraph.MovieRoute(it.id))
            },
            onShowAll = {
                val queryParams = listOf(
                    Constants.IS_SERIES_FIELD to Constants.TRUE,
                    Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                    Constants.SORT_TYPE to Constants.SORT_DESC
                )

                navController.navigate(
                    MovieListGraph.MovieListRoute(
                        title = title,
                        queryParameters = queryParams
                    )
                )
            }
        )
        Spacer(modifier = Modifier.height(130.dp))
    }
}