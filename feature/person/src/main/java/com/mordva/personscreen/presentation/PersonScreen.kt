package com.mordva.personscreen.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mordva.util.Constants
import com.example.movieapp.ui.R
import com.mordva.navigation.AwardListGraph
import com.mordva.navigation.MovieGraph
import com.mordva.navigation.MovieListGraph
import com.mordva.personscreen.presentation.navigation.PersonDetailRoute
import com.mordva.personscreen.presentation.widget.content.CategoriesHeader
import com.mordva.personscreen.presentation.widget.content.MainPersonContent
import com.mordva.personscreen.presentation.widget.listItem.TotalListItem
import com.mordva.ui.uiState.PersonUIState
import com.mordva.ui.widget.bottomSheets.FactSheet
import com.mordva.ui.widget.listItems.ShortMovieListItem
import com.mordva.ui.widget.other.TitleTopBarText
import com.mordva.ui.widget.renderState.RenderFactStateRow
import com.mordva.ui.widget.renderState.RenderMovieStateRow
import com.mordva.ui.widget.shimmer.ShimmerPersonContent
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PersonScreen(
    navController: NavController,
    viewModel: PersonViewModel,
    hazeState: HazeState,
    id: Int
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lazyState = rememberLazyListState()
    val firstOffset by remember { derivedStateOf { lazyState.firstVisibleItemScrollOffset } }
    val index by remember { derivedStateOf { lazyState.firstVisibleItemIndex } }
    var topBarTitle by remember { mutableStateOf("") }
    var selectedFact by remember { mutableStateOf("") }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getPerson(id)
        viewModel.getMovies(id)
        viewModel.getCountAwards(id)
    }

    LaunchedEffect(index, firstOffset) {
        if (index == 0) {
            if (firstOffset > 150) {
                topBarTitle = (uiState.personState as? PersonUIState.Success)
                    ?.data
                    ?.first()
                    ?.name ?: ""
            } else {
                topBarTitle = ""
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    AnimatedContent(
                        targetState = topBarTitle
                    ) { targetState ->
                        TitleTopBarText(text = targetState)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .hazeSource(hazeState),
            state = lazyState
        ) {
            item {
                RenderPersonContent(
                    state = uiState.personState,
                    onClickDetail = {
                        navController.navigate(PersonDetailRoute(id)) {
                            launchSingleTop = true
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                uiState.countAwards?.let {
                    TotalListItem(
                        title = stringResource(R.string.awards),
                        value = it.toString(),
                        modifier = Modifier.clickable {
                            navController.navigate(
                                AwardListGraph.AwardsListRoute(
                                    id = id,
                                    isMovie = false
                                )
                            ) { launchSingleTop = true }
                        }
                    )
                }
            }

            item {
                RenderMovieStateRow(
                    state = uiState.moviesState,
                    title = stringResource(R.string.best_movies_and_serials),
                    onClick = { },
                    onShowAll = {
                        val query = listOf(
                            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                            Constants.SORT_TYPE to Constants.SORT_DESC,
                            Constants.PERSONS_ID_FIELD to id.toString()
                        )

                        navController.navigate(
                            MovieListGraph.MovieListRoute(
                                queryParameters = query,
                                title = "Фильмы: ${
                                    (uiState.personState as PersonUIState.Success)
                                        .data
                                        .first()
                                        .name
                                }"
                            )
                        ) { launchSingleTop = true }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                RenderFactStateRow(
                    state = uiState.factState,
                    title = stringResource(R.string.best_movies_and_serials),
                    onClick = { selectedFact = it.value }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                Text(
                    text = stringResource(R.string.filmography),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
            }

            stickyHeader {
                CategoriesHeader(
                    groups = uiState.groups,
                    selected = uiState.selectedGroup,
                    keys = uiState.groupsKeys,
                    onClick = { viewModel.updateSelectedGroup(it) }
                )
            }

            items(
                items = uiState.moviesFromGroup,
                key = { it.id }
            ) {
                ShortMovieListItem(
                    shortMovie = it,
                    onClick = {
                        navController.navigate(MovieGraph.MovieRoute(it.id)) {
                            launchSingleTop = true
                        }
                    }
                )

                HorizontalDivider()
            }

            item {
                Spacer(modifier = Modifier.height(130.dp))
            }
        }

        if (selectedFact.isNotEmpty()) {
            FactSheet(
                text = selectedFact,
                onDismissRequest = { selectedFact = "" }
            )
        }
    }
}

@Composable
private fun RenderPersonContent(
    state: PersonUIState,
    onClickDetail: () -> Unit
) {
    when (state) {
        PersonUIState.Loading -> ShimmerPersonContent()
        is PersonUIState.Success -> {
            MainPersonContent(
                person = state.data.first(),
                onClickDetail = onClickDetail
            )
        }
    }
}