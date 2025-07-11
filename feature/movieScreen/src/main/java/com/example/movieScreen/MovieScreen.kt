package com.example.movieScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.common.Constants
import com.example.model.movie.Movie
import com.example.model.totalValue.Rating
import com.example.model.totalValue.Votes
import com.example.navigationroute.MovieListRoute
import com.example.navigationroute.PersonRoute
import com.example.ui.uiState.CollectionUIState
import com.example.ui.uiState.ImageUIState
import com.example.ui.uiState.MovieUIState
import com.example.ui.widget.chips.RatingCardLarge
import com.example.movieScreen.widget.collapsingTopBar.CollapsedTopBar
import com.example.movieScreen.widget.collapsingTopBar.ExpandedContent
import com.example.movieScreen.widget.component.PersonGridHorizontalList
import com.example.movieScreen.widget.component.PremierListContent
import com.example.movieScreen.widget.listItem.CommentCard
import com.example.movieapp.movieScreen.R
import com.example.ui.uiState.CommentUIState
import com.example.ui.widget.bottomSheets.FactSheet
import com.example.ui.widget.component.BackdropContent
import com.example.ui.widget.component.BasicLoadingBox
import com.example.ui.widget.component.MovieDescription
import com.example.ui.widget.component.RatingMovieContentRow
import com.example.ui.widget.component.SeasonDescription
import com.example.ui.widget.component.TitleRow
import com.example.ui.widget.component.WatchabilityDescription
import com.example.ui.widget.lazyComponent.DefaultLazyRow
import com.example.ui.widget.listItems.CollectionListItem
import com.example.ui.widget.listItems.FactCard
import com.example.ui.widget.listItems.LastItemCard
import com.example.ui.widget.listItems.MovieCard
import com.example.ui.widget.listItems.SupportPersonCard
import com.example.ui.widget.other.TitleTopBarText
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MovieViewModel,
    hazeState: HazeState,
    id: Int
) {
    val movieState by viewModel.movieState.collectAsStateWithLifecycle()
    val imageState by viewModel.imagesState.collectAsStateWithLifecycle()
    val collectionState by viewModel.collectionState.collectAsStateWithLifecycle()
    val commentState by viewModel.commentState.collectAsStateWithLifecycle()
    val actors by viewModel.actors.collectAsStateWithLifecycle()
    val voiceActors by viewModel.voiceActors.collectAsStateWithLifecycle()
    val supportPersonal by viewModel.supportPersonal.collectAsStateWithLifecycle()
    val selectedFact by viewModel.selectedFact.collectAsStateWithLifecycle()

    val scrollState = rememberLazyListState()
    val firstOffset by remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }
    val index by remember { derivedStateOf { scrollState.firstVisibleItemIndex } }
    var isCollapsed by remember { mutableStateOf(false) }

    LaunchedEffect(firstOffset, index) {
        if (index == 0) {
            if (firstOffset > 800) {
                isCollapsed = true
            }
            else {
                isCollapsed = false
            }
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getMovie(id)
        viewModel.getImages(id)
        viewModel.getComments(id)
    }

    LaunchedEffect(viewModel.movieState) {
        movieState.body()?.let {
            viewModel.getCollections(it.lists)
        }
    }

    RenderMovieContent(state = movieState)

    movieState.body()?.let { movie ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(hazeState)
        ) {
            BackdropContent(
                scrollState = scrollState,
                movie = movie
            )

            CollapsedTopBar(
                isCollapsed = isCollapsed,
                title = { TitleTopBarText(text = movie.name ?: "") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {  }
            )

            LazyColumn(
                modifier = Modifier.hazeSource(hazeState),
                state = scrollState
            ) {
                item {
                    ExpandedContent(movie)
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))

                    MovieDescription(
                        title = movie.shortDescription ?: "",
                        description = movie.description ?: ""
                    ) { }

                    Spacer(modifier = Modifier.height(15.dp))
                }

                item {
                    if (movie.isSeries == false) return@item

                    movie.seasonsInfo?.let { seasonsInfo ->
                        SeasonDescription(
                            modifier = Modifier.clickable {  },
                            countSeasons = seasonsInfo.filter { it.number != 0 }.size,
                            countSeries = seasonsInfo
                                .filter { it.number != 0 }
                                .sumOf { it.episodesCount ?: 0 }
                        )
                    }
                }

                item {
                    if (movie.watchability.items.isEmpty()) return@item

                    WatchabilityDescription(
                        modifier = Modifier.clickable {  },
                        count = movie.watchability.items.size
                    )
                }

                item {
                    RatingCardLarge(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        title = stringResource(R.string.rating_kinopoisk),
                        rating = movie.rating?.kp ?: 0f,
                        votes = movie.votes?.kp ?: 0,
                        onClick = {}
                    )
                }

                item {
                    movie.rating?.let {
                        RatingMovieContentRow(
                            contentPadding = PaddingValues(horizontal = 15.dp, vertical = 10.dp),
                            rating = movie.rating ?: Rating(),
                            votes = movie.votes ?: Votes()
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

                item {
                    if (actors.isNotEmpty()) {
                        TitleRow(title = stringResource(R.string.actors)) {

                        }

                        PersonGridHorizontalList(
                            list = actors.take(9),
                            onClick = {
                                navController.navigate(PersonRoute(it.id)) {
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }

                item {
                    if (supportPersonal.isNotEmpty()) {
                        TitleRow(title = stringResource(R.string.support_personal)) {

                        }

                        DefaultLazyRow(
                            list = supportPersonal.take(10),
                            key = { it.id },
                            lastItemCard = {
                                LastItemCard(
                                    width = 180.dp,
                                    height = 110.dp
                                )
                            }
                        ) {
                            SupportPersonCard(
                                person = it,
                                onClick = {
                                    navController.navigate(PersonRoute(it.id)) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }
                    }
                }

                item {
                    if (voiceActors.isNotEmpty()) {
                        TitleRow(title = stringResource(R.string.voice_actors)) {

                        }

                        DefaultLazyRow(
                            list = voiceActors.take(10),
                            key = { it.id },
                            lastItemCard = {
                                LastItemCard(
                                    width = 180.dp,
                                    height = 110.dp
                                )
                            }
                        ) {
                            SupportPersonCard(
                                person = it,
                                onClick = {
                                    navController.navigate(PersonRoute(it.id)) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }

                item {
                    (commentState as? CommentUIState.Success)?.data?.let {
                        TitleRow(title = stringResource(R.string.review)) {}

                        DefaultLazyRow(
                            list = it,
                            lastItemCard = {}
                        ) { comment ->
                            CommentCard(comment)
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }

                item {
                    (imageState as? ImageUIState.Success)?.data?.let {
                        if (it.isNotEmpty()) {
                            TitleRow(title = stringResource(R.string.images)) {

                            }

                            DefaultLazyRow(
                                list = it,
                                lastItemCard = {
                                    LastItemCard(
                                        width = 200.dp,
                                        height = 160.dp
                                    )
                                }
                            ) { poster ->
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(poster.url)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    error = painterResource(R.drawable.ic_image),
                                    modifier = Modifier
                                        .height(160.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                )
                            }
                        }
                    }
                }

                item {
                    (collectionState as? CollectionUIState.Success)?.data?.let { data ->
                        Spacer(modifier = Modifier.height(30.dp))

                        TitleRow(title = stringResource(R.string.in_lists)) {

                        }

                        LazyHorizontalGrid(
                            rows = GridCells.Fixed(3),
                            modifier = Modifier.height(290.dp)
                        ) {
                            items(data) {
                                CollectionListItem(
                                    collectionMovie = it,
                                    modifier = Modifier
                                        .width(300.dp)
                                        .clickable {
                                            val query = arrayListOf(
                                                Constants.LISTS_FIELD to it.slug.toString(),
                                                Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                                                Constants.SORT_TYPE to Constants.SORT_DESC
                                            )

                                            navController.navigate(
                                                MovieListRoute(
                                                    title = it.name ?: "",
                                                    queryParameters = query
                                                )
                                            ) { launchSingleTop = true }
                                        }
                                )
                            }
                        }
                    }
                }

                item {
                    if (movie.facts.isEmpty()) return@item

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = stringResource(R.string.similar_movies),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(15.dp)
                    )

                    DefaultLazyRow(
                        list = movie.facts,
                        key = { it.value },
                        lastItemCard = {}
                    ) {
                        FactCard(
                            text = it.value,
                            isSpoiler = it.spoiler ?: false,
                            onClick = {  }
                        )
                    }
                }

                item {

                }

                item {
                    movie.premiere?.let {
                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = stringResource(R.string.rental),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(15.dp)
                        )

                        PremierListContent(it)
                    }
                }

                item {
                    if (movie.similarMovies.isEmpty()) return@item

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = stringResource(R.string.similar_movies),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(15.dp)
                    )

                    DefaultLazyRow(
                        list = movie.similarMovies,
                        lastItemCard = {},
                    ) {
                        MovieCard(it)
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(130.dp))
                }
            }
        }
    }

    if (selectedFact.isNotEmpty()) {
        FactSheet(
            text = selectedFact,
            onDismissRequest = {
                viewModel.updateSelectedFact("")
            }
        )
    }
}

private fun MovieUIState.body(): Movie? {
    return (this as? MovieUIState.Success)?.data?.first()
}

@Composable
private fun RenderMovieContent(
    modifier: Modifier = Modifier,
    state: MovieUIState
) {
    when (state) {
        MovieUIState.Loading -> BasicLoadingBox(modifier)
        is MovieUIState.Success -> {}
    }
}