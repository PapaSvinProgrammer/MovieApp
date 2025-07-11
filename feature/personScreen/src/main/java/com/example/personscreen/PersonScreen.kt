package com.example.personscreen

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.navigation.NavController
import com.example.common.Constants
import com.example.movieapp.personScreen.R
import com.example.navigationroute.AwardListRoute
import com.example.navigationroute.MovieListRoute
import com.example.navigationroute.MovieRoute
import com.example.navigationroute.PersonDetailRoute
import com.example.ui.uiState.PersonUIState
import com.example.ui.widget.bottomSheets.FactSheet
import com.example.ui.widget.component.CategoriesHeader
import com.example.ui.widget.listItems.ShortMovieListItem
import com.example.ui.widget.listItems.TotalListItem
import com.example.ui.widget.other.TitleTopBarText
import com.example.ui.widget.renderState.RenderFactStateRow
import com.example.ui.widget.renderState.RenderMovieStateRow
import com.example.ui.widget.shimmer.ShimmerPersonContent
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource
import com.example.personscreen.widget.MainPersonContent

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonScreen(
    navController: NavController,
    viewModel: PersonViewModel,
    hazeState: HazeState,
    id: Int
) {
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
                topBarTitle = (viewModel.personState as? PersonUIState.Success)
                    ?.data
                    ?.first()
                    ?.name ?: ""
            }
            else {
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
            modifier = Modifier.padding(innerPadding).hazeSource(hazeState),
            state = lazyState
        ) {
            item {
                RenderPersonContent(
                    state = viewModel.personState,
                    onClickDetail = {
                        navController.navigate(PersonDetailRoute(id)) {
                            launchSingleTop = true
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                viewModel.countAwards?.let {
                    TotalListItem(
                        title = stringResource(R.string.awards),
                        value = it.toString(),
                        modifier = Modifier.clickable {
                            navController.navigate(
                                AwardListRoute(
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
                    state = viewModel.moviesState,
                    title = stringResource(R.string.best_movies_and_serials),
                    onClick = {  },
                    onShowAll = {
                        val query = listOf(
                            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                            Constants.SORT_TYPE to Constants.SORT_DESC,
                            Constants.PERSONS_ID_FIELD to id.toString()
                        )

                        navController.navigate(
                            MovieListRoute(
                                queryParameters = query,
                                title = "Фильмы: ${
                                    (viewModel.personState as PersonUIState.Success)
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
                    state = viewModel.factState,
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
                    groups = viewModel.groups,
                    selected = viewModel.selectedGroup,
                    keys = viewModel.groupsKeys,
                    onClick = { viewModel.updateSelectedGroup(it) }
                )
            }

            items(
                items = viewModel.moviesFromGroup,
                key = { it.id }
            ) {
                ShortMovieListItem(
                    shortMovie = it,
                    onClick = {
                        navController.navigate(MovieRoute(it.id)) {
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