package com.example.movieapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.movieapp.ui.screen.uiState.PersonUIState
import com.example.movieapp.viewModels.PersonListViewModel
import com.example.movieapp.ui.widget.lazyComponent.EndlessLazyColumn
import com.example.movieapp.ui.widget.listItems.PersonListItem
import com.example.movieapp.ui.widget.other.TitleTopBarText
import com.example.movieapp.ui.widget.shimmer.ShimmerMovieDetailList
import com.example.network.module.person.Person

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonPodiumListScreen(
    navController: NavController,
    viewModel: PersonListViewModel,
    title: String,
    queryParameters: List<Pair<String, String>>
) {
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getPersons(queryParameters)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = title)
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
        RenderPersonResult(
            modifier = Modifier.padding(innerPadding),
            state = viewModel.personState,
            onClick = {},
            onLoadMore = { viewModel.loadMorePersons(queryParameters) }
        )
    }
}

@Composable
private fun RenderPersonResult(
    modifier: Modifier,
    state: PersonUIState,
    onLoadMore: () -> Unit,
    onClick: (Person) -> Unit
) {
    when (state) {
        PersonUIState.Loading -> ShimmerMovieDetailList(modifier)
        is PersonUIState.Success -> {
            MainPersonContent(
                modifier = modifier,
                list = state.data,
                onLoadMore = onLoadMore,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun MainPersonContent(
    modifier: Modifier,
    list: List<Person>,
    onLoadMore: () -> Unit,
    onClick: (Person) -> Unit
) {
    EndlessLazyColumn(
        modifier = modifier,
        items = list,
        loadMore = onLoadMore
    ) { index, item ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
                .padding(horizontal = 15.dp, vertical = 8.dp)
        ) {
            Text(
                text = (index + 1).toString(),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Medium
            )
        }

        PersonListItem(
            person = item,
            onClick = { onClick(item) }
        )
    }
}