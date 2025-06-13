package com.example.movieapp.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.movieapp.app.navigation.MovieListRoute
import com.example.movieapp.ui.screen.uiState.CollectionUIState
import com.example.movieapp.ui.widget.lazyComponent.EndlessLazyColumn
import com.example.movieapp.ui.widget.listItems.CollectionListItem
import com.example.movieapp.ui.widget.other.TitleTopBarText
import com.example.movieapp.ui.widget.shimmer.ShimmerMovieDetailList
import com.example.movieapp.viewModels.CollectionListViewModel
import com.example.network.module.image.Collection
import com.example.network.utils.Constants
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionListScreen(
    navController: NavController,
    viewModel: CollectionListViewModel,
    hazeState: HazeState,
    category: String? = null
) {
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getCollections(category)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    val text = if (category == null)
                        "Все коллекции"
                    else
                        "Коллекции: $category"

                    TitleTopBarText(text)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        RenderCollectionState(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .hazeSource(hazeState),
            state = viewModel.collectionState,
            onClick = {
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
                )
            },
            onLoadMore = { viewModel.loadMoreCollections(category) }
        )
    }
}

@Composable
private fun RenderCollectionState(
    modifier: Modifier = Modifier,
    state: CollectionUIState,
    onClick: (Collection) -> Unit,
    onLoadMore: () -> Unit
) {
    when (state) {
        CollectionUIState.Loading -> ShimmerMovieDetailList(modifier)
        is CollectionUIState.Success -> {
            MainPersonContent(
                modifier = modifier,
                collections = state.data,
                onClick = onClick,
                onLoadMore = onLoadMore
            )
        }
    }
}

@Composable
private fun MainPersonContent(
    modifier: Modifier,
    collections: List<Collection>,
    onLoadMore: () -> Unit,
    onClick: (Collection) -> Unit
) {
    EndlessLazyColumn(
        modifier = modifier,
        items = collections,
        loadMore = onLoadMore
    ) { index, item ->
        CollectionListItem(
            index = index + 1,
            collection = item,
            onClick = { onClick(item) }
        )
    }
}