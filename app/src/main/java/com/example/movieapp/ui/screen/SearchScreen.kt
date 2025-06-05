package com.example.movieapp.ui.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.R
import com.example.movieapp.app.navigation.CollectionListRoute
import com.example.movieapp.app.navigation.SearchSettingsRoute
import com.example.movieapp.app.utils.collectionCategoryList
import com.example.movieapp.ui.screen.uiState.PersonUIState
import com.example.movieapp.ui.viewModel.SearchViewModel
import com.example.movieapp.ui.widget.lazyComponent.DefaultLazyRow
import com.example.movieapp.ui.widget.component.TitleRow
import com.example.movieapp.ui.widget.component.SearchBarContent
import com.example.movieapp.ui.widget.listItems.LastItemCard
import com.example.movieapp.ui.widget.listItems.PersonCard
import com.example.movieapp.ui.widget.shimmer.ShimmerMovieRow
import com.example.network.module.person.Person
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel,
    hazeState: HazeState
) {
    val searchHistoryLazyPaging = viewModel.resultHistory.collectAsLazyPagingItems()

    LaunchedEffect(viewModel.isExpanded) {
        if (!viewModel.isExpanded) {
            viewModel.clearSearchResult()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            expanded = viewModel.isExpanded,
            onExpandedChange = { viewModel.updateExpanded(it) },
            inputField = {
                SearchBarDefaults.InputField(
                    query = viewModel.query,
                    onQueryChange = {
                        viewModel.updateQuery(it)
                        viewModel.search(it)
                    },
                    onSearch = {
                        viewModel.updateExpanded(false)
                        viewModel.updateQuery("")
                    },
                    expanded = viewModel.isExpanded,
                    onExpandedChange = { viewModel.updateExpanded(it) },
                    placeholder = { Text(stringResource(R.string.search_hint)) },
                    leadingIcon = {
                        LeadingIcon(
                            expanded = viewModel.isExpanded,
                            onOpen = { viewModel.updateExpanded(true) },
                            onClose = {
                                viewModel.updateExpanded(false)
                                viewModel.updateQuery("")
                            }
                        )
                    },
                    trailingIcon = {
                        TrailingIcon(
                            expanded = viewModel.isExpanded,
                            onSettings = { navController.navigate(SearchSettingsRoute) },
                            onClear = {
                                if (viewModel.query.isEmpty()) {
                                    viewModel.updateExpanded(false)
                                }
                                else {
                                    viewModel.updateQuery("")
                                }
                            }
                        )
                    }
                )
            },
            content = {
                SearchBarContent(
                    query = viewModel.query,
                    hazeState = hazeState,
                    movieSearchState = viewModel.searchState,
                    searchHistoryLazyPaging = searchHistoryLazyPaging,
                    selectedItem = viewModel.selectedSearchIndex,
                    onDeleteHistoryItem = {
                        viewModel.deleteSearchHistoryItem(it)
                    },
                    onClick = {
                        viewModel.insertSearchHistoryItem(it)
                    },
                    onLoadMore = { viewModel.loadMore() },
                    onSelectItem = {
                        viewModel.updateSelectedSearchIndex(it)
                        viewModel.search(viewModel.query)
                    }
                )
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .haze(hazeState),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                viewModel.getCollections()
                RenderCollectionRowState(
                    state = viewModel.collectionsState,
                    title = stringResource(R.string.advise_watch),
                    onClick = {},
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
                            onClick = {}
                        )
                    }
                }
            }

            item {
                viewModel.getActorByCountAwards()
                RenderPersonRowState(
                    state = viewModel.dubbingPersonState,
                    title = stringResource(R.string.have_most_awards),
                    onClick = {},
                    onShowAll = {}
                )
            }

            item {
                viewModel.getTopSerials()
                RenderMovieRowState(
                    state = viewModel.topSerialsState,
                    title = stringResource(R.string.popular_serials),
                    onClick = {},
                    onShowAll = {}
                )
                Spacer(modifier = Modifier.height(130.dp))
            }
        }
    }
}

@Composable
fun RenderPersonRowState(
    state: PersonUIState,
    title: String,
    onClick: (Person) -> Unit,
    onShowAll: () -> Unit
) {
    when (state) {
        PersonUIState.Loading -> ShimmerMovieRow()
        is PersonUIState.Success -> {
            PersonRow(
                persons = state.data,
                title = title,
                onClick = onClick,
                onShowAll = onShowAll
            )
        }
    }
}

@Composable
private fun PersonRow(
    modifier: Modifier = Modifier,
    persons: List<Person>,
    title: String,
    onClick: (Person) -> Unit,
    onShowAll: () -> Unit
) {
    TitleRow(title = title) {

    }

    DefaultLazyRow(
        modifier = modifier,
        list = persons,
        lastItemCard = {
            LastItemCard(
                width = 160.dp,
                height = 250.dp,
                onClick = onShowAll
            )
        },
        content = {
            PersonCard(person = it) { onClick(it) }
        }
    )
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

    IconButton(onClick = { if (expanded) onClear() else onSettings()}) {
        Icon(
            painter = icon,
            contentDescription = null
        )
    }
}