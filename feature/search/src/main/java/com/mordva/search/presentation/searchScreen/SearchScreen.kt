package com.mordva.search.presentation.searchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mordva.navigation.MovieGraph
import com.mordva.navigation.PersonGraph
import com.mordva.search.presentation.navigation.SearchSettingsRoute
import com.mordva.search.presentation.searchScreen.widget.component.SearchBarContent
import com.mordva.search.presentation.searchScreen.widget.component.collectionCategoryListItemContent
import com.mordva.search.presentation.searchScreen.widget.component.collectionsItemContent
import com.mordva.search.presentation.searchScreen.widget.component.serialsItemContent
import com.mordva.ui.widget.component.CustomSearchBar
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel,
    hazeState: HazeState
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isExpanded) {
        if (!uiState.isExpanded) {
            viewModel.clearSearchResult()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        CustomSearchBar(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            expanded = uiState.isExpanded,
            onExpandedChange = { viewModel.updateExpanded(it) },
            query = uiState.query,
            onQueryChange = {
                viewModel.updateQuery(it)
                viewModel.search()
            },
            onSearch = {
                viewModel.updateExpanded(false)
                viewModel.updateQuery("")
            },
            onOpen = { viewModel.updateExpanded(true) },
            onClose = {
                viewModel.updateExpanded(false)
                viewModel.updateQuery("")
            },
            onSettings = {
                navController.navigate(SearchSettingsRoute)
            },
            onClear = {
                if (uiState.query.isEmpty()) {
                    viewModel.updateExpanded(false)
                } else {
                    viewModel.updateQuery("")
                }
            },
            content = {
                SearchBarContent(
                    query = uiState.query,
                    hazeState = hazeState,
                    movieSearchState = uiState.searchState,
                    searchHistoryList = listOf(),
                    selectedItem = uiState.selectedSearchIndex,
                    onDeleteHistoryItem = {
                        viewModel.deleteSearchHistoryItem(it)
                    },
                    onClick = {
                        if (it.isMovie) {
                            navController.navigate(MovieGraph.MovieRoute(it.id)) {
                                launchSingleTop = true
                            }
                        } else {
                            navController.navigate(PersonGraph.PersonRoute(it.id)) {
                                launchSingleTop = true
                            }
                        }

                        viewModel.insertSearchHistoryItem(it)
                    },
                    onLoadMore = { viewModel.loadMore() },
                    onSelectItem = {
                        viewModel.updateSelectedSearchIndex(it)
                        viewModel.search()
                    }
                )
            },
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .hazeSource(hazeState),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            collectionsItemContent(
                state = uiState.collectionsState,
                navController = navController,
                get = { viewModel.getCollections() }
            )

            collectionCategoryListItemContent(navController)

            serialsItemContent(
                state = uiState.topSerialsState,
                navController = navController,
                get = { viewModel.getTopSerials() }
            )
        }
    }
}
