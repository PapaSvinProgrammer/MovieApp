package com.example.collectionlist.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.collectionlist.presentation.widget.MainCollectionContent
import com.example.movieapp.ui.R
import com.example.ui.widget.other.TitleTopBarText
import dev.chrisbanes.haze.HazeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CollectionListScreen(
    navController: NavController,
    viewModel: CollectionListViewModel,
    hazeState: HazeState
) {
    val collectionState by viewModel.collectionState.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getAllCollections()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(stringResource(R.string.collections))
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
        MainCollectionContent(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            hazeState = hazeState,
            collectionState = collectionState,
            navController = navController,
            onLoadMore = { viewModel.loadMoreAllCollections() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CollectionListScreen(
    navController: NavController,
    viewModel: CollectionListViewModel,
    hazeState: HazeState,
    category: String
) {
    val collectionState by viewModel.collectionState.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getCollectionsByCategory(category)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(stringResource(R.string.collection, category))
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
        MainCollectionContent(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            hazeState = hazeState,
            collectionState = collectionState,
            navController = navController,
            onLoadMore = { viewModel.loadMoreCollectionsByCategory(category) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CollectionListScreen(
    navController: NavController,
    viewModel: CollectionListViewModel,
    hazeState: HazeState,
    listId: List<String>
) {
    val collectionState by viewModel.collectionState.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getCollectionsByListId(listId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(stringResource(R.string.collections))
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
        MainCollectionContent(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            hazeState = hazeState,
            collectionState = collectionState,
            navController = navController
        )
    }
}
