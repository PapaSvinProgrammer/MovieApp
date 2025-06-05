package com.example.movieapp.ui.screen

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
import com.example.movieapp.ui.screen.uiState.CollectionUIState
import com.example.movieapp.ui.viewModel.CollectionListViewModel
import com.example.movieapp.ui.widget.lazyComponent.EndlessLazyColumn
import com.example.movieapp.ui.widget.listItems.CollectionRowCard
import com.example.movieapp.ui.widget.other.TitleTopBarText
import com.example.movieapp.ui.widget.shimmer.ShimmerMovieDetailList
import com.example.network.module.image.Collection
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

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
            modifier = Modifier.padding(innerPadding).haze(hazeState),
            state = viewModel.collectionState,
            onClick = {},
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
            MainContent(
                modifier = modifier,
                collections = state.data,
                onClick = onClick,
                onLoadMore = onLoadMore
            )
        }
    }
}

@Composable
private fun MainContent(
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
        CollectionRowCard(
            index = index + 1,
            collection = item,
            onClick = { onClick(item) }
        )
    }
}