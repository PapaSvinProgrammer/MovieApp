package com.example.personpodium

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.person.Person
import com.example.ui.uiState.PersonUIState
import com.example.ui.widget.lazyComponent.EndlessLazyColumn
import com.example.ui.widget.listItems.PersonListItem
import com.example.ui.widget.shimmer.ShimmerMovieDetailList

@Composable
internal fun RenderPersonResult(
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