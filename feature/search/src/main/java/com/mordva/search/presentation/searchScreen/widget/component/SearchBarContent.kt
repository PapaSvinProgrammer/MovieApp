package com.mordva.search.presentation.searchScreen.widget.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.R
import com.mordva.model.History
import com.mordva.model.SearchItem
import com.mordva.ui.uiState.SearchUIState
import dev.chrisbanes.haze.HazeState

@Composable
fun SearchBarContent(
    query: String,
    searchHistoryList: List<History>,
    hazeState: HazeState,
    movieSearchState: SearchUIState,
    selectedItem: Int,
    onSelectItem: (Int) -> Unit,
    onDeleteHistoryItem: (Int) -> Unit,
    onClick: (SearchItem) -> Unit,
    onLoadMore: () -> Unit
) {
    val options = listOf(
        stringResource(R.string.cinema),
        stringResource(R.string.persons)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        if (query.isNotEmpty()) {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                options.forEachIndexed { index, s ->
                    SegmentedButton(
                        label = { Text(text = s) },
                        icon = {},
                        selected = index == selectedItem,
                        onClick = { onSelectItem(index) },
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = options.size,
                            baseShape = RoundedCornerShape(10.dp)
                        )
                    )
                }
            }
        }

        if (query.isEmpty()) {
            SearchHistoryContent(
                list = searchHistoryList,
                hazeState = hazeState,
                onClick = onClick,
                onRemoveClick = onDeleteHistoryItem
            )
        } else {
            RenderSearchResult(
                state = movieSearchState,
                hazeState = hazeState,
                onClick = onClick,
                onLoadMore = onLoadMore
            )
        }
    }
}

@Composable
private fun RenderSearchResult(
    state: SearchUIState,
    onClick: (SearchItem) -> Unit,
    onLoadMore: () -> Unit,
    hazeState: HazeState
) {
    when (state) {
        SearchUIState.Error -> ErrorSearchContent()
        SearchUIState.Loading -> LoadingSearchContent()
        is SearchUIState.Success -> {
            SearchContent(
                list = state.data,
                onClick = onClick,
                onLoadMore = onLoadMore,
                hazeState = hazeState
            )
        }
    }
}