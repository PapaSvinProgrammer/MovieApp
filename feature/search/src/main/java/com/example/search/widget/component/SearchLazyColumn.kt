package com.example.search.widget.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
internal fun SearchLazyColumn(
    query: String,
    result: List<ListUIState>,
    onClick: (Int) -> Unit
) {
    val searchResult: List<ListUIState> = result
        .filter {
            it.title.lowercase().contains(
                query.lowercase()
            )
        }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(searchResult) {
            var clickable by remember { mutableStateOf(it.isChecked) }

            ListItem(
                headlineContent = {
                    Text(text = it.title)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        clickable = !clickable
                        onClick(result.indexOf(it))
                    },
                trailingContent = {
                    if (clickable) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                }
            )

            HorizontalDivider()
        }
    }
}