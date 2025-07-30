package com.example.search.searchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.utils.Constants
import com.example.model.image.CollectionMovie
import com.example.movieapp.ui.R
import com.example.navigationroute.CollectionListRoute
import com.example.navigationroute.MovieListRoute
import com.example.navigationroute.MovieRoutes
import com.example.navigationroute.PersonPodiumListRoute
import com.example.navigationroute.PersonRoute
import com.example.navigationroute.SearchRoutes
import com.example.search.searchScreen.widget.component.SearchBarContent
import com.example.ui.widget.renderState.RenderPersonRowState
import com.example.ui.widget.renderState.RenderCollectionStateRow
import com.example.ui.widget.renderState.RenderMovieStateRow
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel,
    hazeState: HazeState
) {
    val searchHistoryLazyPaging = viewModel.resultHistory.collectAsLazyPagingItems()
    val query by viewModel.query.collectAsStateWithLifecycle()
    val isExpanded by viewModel.isExpanded.collectAsStateWithLifecycle()
    val collectionState by viewModel.collectionsState.collectAsStateWithLifecycle()
    val personState by viewModel.personState.collectAsStateWithLifecycle()
    val topSerialsState by viewModel.topSerialsState.collectAsStateWithLifecycle()
    val selectedSearchIndex by viewModel.selectedSearchIndex.collectAsStateWithLifecycle()
    val searchState by viewModel.searchState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.isExpanded) {
        if (!isExpanded) {
            viewModel.clearSearchResult()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            expanded = isExpanded,
            onExpandedChange = { viewModel.updateExpanded(it) },
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = {
                        viewModel.updateQuery(it)
                        viewModel.search(it)
                    },
                    onSearch = {
                        viewModel.updateExpanded(false)
                        viewModel.updateQuery("")
                    },
                    expanded = isExpanded,
                    onExpandedChange = { viewModel.updateExpanded(it) },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.search_hint),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    leadingIcon = {
                        LeadingIcon(
                            expanded = isExpanded,
                            onOpen = { viewModel.updateExpanded(true) },
                            onClose = {
                                viewModel.updateExpanded(false)
                                viewModel.updateQuery("")
                            }
                        )
                    },
                    trailingIcon = {
                        TrailingIcon(
                            expanded = isExpanded,
                            onSettings = {
                                navController.navigate(SearchRoutes.SearchSettingsRoute)
                            },
                            onClear = {
                                if (query.isEmpty()) {
                                    viewModel.updateExpanded(false)
                                } else {
                                    viewModel.updateQuery("")
                                }
                            }
                        )
                    }
                )
            },
            content = {
                SearchBarContent(
                    query = query,
                    hazeState = hazeState,
                    movieSearchState = searchState,
                    searchHistoryLazyPaging = searchHistoryLazyPaging,
                    selectedItem = selectedSearchIndex,
                    onDeleteHistoryItem = {
                        viewModel.deleteSearchHistoryItem(it)
                    },
                    onClick = {
                        if (it.isMovie) {
                            navController.navigate(MovieRoutes.MovieRoute(it.id)) {
                                launchSingleTop = true
                            }
                        } else {
                            navController.navigate(PersonRoute(it.id)) {
                                launchSingleTop = true
                            }
                        }

                        viewModel.insertSearchHistoryItem(it)
                    },
                    onLoadMore = { viewModel.loadMore() },
                    onSelectItem = {
                        viewModel.updateSelectedSearchIndex(it)
                        viewModel.search(query)
                    }
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .hazeSource(hazeState),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                viewModel.getCollections()
                RenderCollectionStateRow(
                    state = collectionState,
                    title = stringResource(R.string.advise_watch),
                    onClick = { navigateToMovieList(navController, it) },
                    onShowAll = {
                        navController.navigate(
                            CollectionListRoute("Фильмы")
                        )
                    }
                )
            }

            item {
                Text(
                    modifier = Modifier.padding(15.dp),
                    text = stringResource(R.string.categories),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    collectionCategoryList.forEach {
                        SuggestionChip(
                            label = { Text(text = it) },
                            onClick = {
                                navController.navigate(
                                    CollectionListRoute(it)
                                )
                            }
                        )
                    }
                }
            }

            item {
                val title = stringResource(R.string.popular_names)
                viewModel.getActorByPopularityMovies()
                RenderPersonRowState(
                    state = personState,
                    title = title,
                    onClick = {
                        navController.navigate(PersonRoute(it.id))
                    },
                    onShowAll = {
                        val queryParameters = listOf(
                            Constants.SORT_FIELD to Constants.MOVIES_RATING_FIELD,
                            Constants.SORT_TYPE to Constants.SORT_DESC
                        )

                        navController.navigate(
                            PersonPodiumListRoute(
                                title = title,
                                queryParameters = queryParameters
                            )
                        )
                    }
                )
            }

            item {
                val title = stringResource(R.string.popular_serials)
                viewModel.getTopSerials()

                RenderMovieStateRow(
                    state = topSerialsState,
                    title = title,
                    onClick = { navController.navigate(MovieRoutes.MovieRoute(it.id)) },
                    onShowAll = {
                        val queryParams = listOf(
                            Constants.IS_SERIES_FIELD to Constants.TRUE,
                            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                            Constants.SORT_TYPE to Constants.SORT_DESC
                        )

                        navController.navigate(
                            MovieListRoute(
                                title = title,
                                queryParameters = queryParams
                            )
                        )
                    }
                )
                Spacer(modifier = Modifier.height(130.dp))
            }
        }
    }
}

@Composable
private fun LeadingIcon(
    expanded: Boolean,
    onClose: () -> Unit,
    onOpen: () -> Unit
) {
    val icon = if (expanded)
        Icons.AutoMirrored.Default.ArrowBack
    else
        Icons.Rounded.Search

    IconButton(
        onClick = { if (expanded) onClose() else onOpen() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Composable
private fun TrailingIcon(
    expanded: Boolean,
    onSettings: () -> Unit,
    onClear: () -> Unit
) {
    val icon = if (expanded)
        painterResource(R.drawable.ic_close)
    else
        painterResource(R.drawable.ic_tune)

    IconButton(onClick = { if (expanded) onClear() else onSettings() }) {
        Icon(
            painter = icon,
            contentDescription = null
        )
    }
}

private fun navigateToMovieList(
    navController: NavController,
    collectionMovie: CollectionMovie
) {
    val query = arrayListOf(
        Constants.LISTS_FIELD to collectionMovie.slug.toString(),
        Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
        Constants.SORT_TYPE to Constants.SORT_DESC
    )

    navController.navigate(
        MovieListRoute(
            title = collectionMovie.name ?: "",
            queryParameters = query
        )
    ) { launchSingleTop = true }
}

private val collectionCategoryList = listOf(
    "Онлайн-кинотеатр",
    "Премии",
    "Сборы",
    "Фильмы",
    "Сериалы"
)