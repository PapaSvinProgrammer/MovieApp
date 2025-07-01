package com.example.ui.widget.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ui.widget.other.TitleTopBarText
import com.example.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextListLayout(
    visible: Boolean,
    title: String,
    list: List<Pair<String, Boolean>>,
    onClose: () -> Unit,
    onReset: () -> Unit,
    onClick: (Int) -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(initialOffsetX = { it }),
        exit = slideOutHorizontally(targetOffsetX = { it })
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        TitleTopBarText(text = title)
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = onClose
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        TextButton(onClick = onReset) {
                            Text(
                                text = stringResource(R.string.reset),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            MainContent(
                innerPadding = innerPadding,
                result = list,
                onClick = { onClick(it) }
            )
        }
    }
}

@Composable
private fun MainContent(
    innerPadding: PaddingValues,
    result: List<Pair<String, Boolean>>,
    onClick: (Int) -> Unit
) {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        CustomSearchBar(
            query = query,
            onValueChange = { query = it }
        )

        Spacer(modifier = Modifier.height(15.dp))

        if (query.isEmpty()) {
            MainLazyColumn(
                result = result,
                onClick = onClick
            )
        }
        else {
            SearchLazyColumn(
                query = query,
                result = result,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun MainLazyColumn(
    result: List<Pair<String, Boolean>>,
    onClick: (Int) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(result.size) { index ->
            ListItem(
                headlineContent = {
                    Text(text = result[index].first)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick(index) },
                trailingContent = {
                    if (result[index].second) {
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

@Composable
private fun SearchLazyColumn(
    query: String,
    result: List<Pair<String, Boolean>>,
    onClick: (Int) -> Unit
) {
    val searchResult: List<Pair<String, Boolean>> = result
        .filter {
            it.first.lowercase().contains(
                query.lowercase()
            )
        }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(searchResult) {
            ListItem(
                headlineContent = {
                    Text(text = it.first)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick(result.indexOf(it)) },
                trailingContent = {
                    if (it.second) {
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
