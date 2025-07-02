package com.example.collectionlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.common.Constants
import com.example.model.image.Collection
import com.example.navigationroute.MovieListRoute
import com.example.ui.uiState.CollectionUIState
import com.example.ui.widget.lazyComponent.EndlessLazyColumn
import com.example.ui.widget.listItems.CollectionListItem
import com.example.ui.widget.other.TitleTopBarText
import com.example.ui.widget.shimmer.ShimmerMovieDetailList
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
                ) { launchSingleTop = true }
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
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(item) },
            leadingIcon = {
                Text(
                    text = (index + 1).toString(),
                    fontSize = 14.sp
                )
            },
            collection = item
        )
    }
}