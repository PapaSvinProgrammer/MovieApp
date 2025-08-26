package com.mordva.collectionlist.presentation.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mordva.navigation.MovieListGraph
import com.mordva.ui.uiState.CollectionUIState
import com.mordva.util.Constants
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCollectionContent(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    collectionState: CollectionUIState,
    navController: NavController,
    onLoadMore: () -> Unit = {}
) {
    RenderCollectionState(
        modifier = modifier
            .fillMaxSize()
            .hazeSource(hazeState),
        state = collectionState,
        onClick = {
            val query = arrayListOf(
                Constants.LISTS_FIELD to it.slug.toString(),
                Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                Constants.SORT_TYPE to Constants.SORT_DESC
            )

            navController.navigate(
                MovieListGraph.MovieListRoute(
                    title = it.name ?: "",
                    queryParameters = query
                )
            ) { launchSingleTop = true }
        },
        onLoadMore = onLoadMore
    )
}