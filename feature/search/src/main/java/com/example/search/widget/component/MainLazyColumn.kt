package com.example.search.widget.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
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
internal fun MainLazyColumn(
    result: List<ListUIState>,
    onClick: (Int) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(result.size) { index ->
            var clickable by remember { mutableStateOf(result[index].isChecked) }

            ListItem(
                headlineContent = {
                    Text(text = result[index].title)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        clickable = !clickable
                        onClick(index)
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