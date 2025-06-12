package com.example.movieapp.ui.widget.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.core.data.room.entity.HistoryEntity
import com.example.core.domain.module.SearchItem
import com.example.core.utils.toSearchItem
import com.example.movieapp.R
import com.example.movieapp.ui.widget.lazyComponent.EndlessLazyColumn
import com.example.movieapp.ui.widget.listItems.SearchHistoryMovieCard
import com.example.movieapp.ui.widget.listItems.SearchItemCard
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun SearchContent(
    list: List<SearchItem>,
    hazeState: HazeState,
    onClick: (SearchItem) -> Unit,
    onLoadMore: () -> Unit
) {
    EndlessLazyColumn(
        items = list,
        loadMore = onLoadMore,
        modifier = Modifier.haze(hazeState)
    ) { _, it ->
        SearchItemCard(
            searchItem = it,
            onClick = { onClick(it) }
        )

        HorizontalDivider(modifier = Modifier.padding(start = 110.dp))
    }
}

@Composable
fun ErrorSearchContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            modifier = Modifier.padding(vertical = 60.dp),
            text = "(^_^)",
            fontSize = 120.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = stringResource(R.string.not_found),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun LoadingSearchContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(vertical = 40.dp)
        )
    }
}

@Composable
fun SearchHistoryContent(
    lazyPaging: LazyPagingItems<HistoryEntity>,
    hazeState: HazeState,
    onClick: (SearchItem) -> Unit,
    onRemoveClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .haze(hazeState)
    ) {
        items(
            count = lazyPaging.itemCount,
            key = lazyPaging.itemKey { it.id },
            contentType = lazyPaging.itemContentType { "Search item" },
            itemContent = { index ->
                val entity = lazyPaging[index]

                entity?.let {
                    SearchHistoryMovieCard(
                        modifier = Modifier.animateItem(),
                        searchItem = it.toSearchItem(),
                        onClick = { onClick(entity.toSearchItem()) },
                        onRemove = { onRemoveClick(it.movieId) }
                    )
                }
            }
        )
    }
}