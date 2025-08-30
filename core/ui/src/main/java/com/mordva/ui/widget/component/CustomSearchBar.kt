package com.mordva.ui.widget.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.movieapp.ui.R
import com.mordva.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    expanded: Boolean,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onOpen: () -> Unit,
    onClose: () -> Unit,
    onSettings: () -> Unit,
    onClear: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    SearchBar(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = onSearch,
                expanded = expanded,
                onExpandedChange = onExpandedChange,
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_hint),
                        maxLines = 1,
                        fontSize = Typography.bodyMedium.fontSize,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                leadingIcon = {
                    LeadingIcon(
                        expanded = expanded,
                        onOpen = onOpen,
                        onClose = onClose
                    )
                },
                trailingIcon = {
                    TrailingIcon(
                        expanded = expanded,
                        onSettings = onSettings,
                        onClear = onClear
                    )
                }
            )
        },
        content = content
    )
}


@Composable
internal fun LeadingIcon(
    expanded: Boolean,
    onClose: () -> Unit,
    onOpen: () -> Unit
) {
    val icon = if (expanded)
        Icons.AutoMirrored.Default.ArrowBack
    else
        Icons.Rounded.Search

    IconButton(
        onClick = { if (expanded) onClose() else onOpen() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Composable
internal fun TrailingIcon(
    expanded: Boolean,
    onSettings: () -> Unit,
    onClear: () -> Unit
) {
    val icon = if (expanded)
        painterResource(R.drawable.ic_close)
    else
        painterResource(R.drawable.ic_tune)

    IconButton(onClick = { if (expanded) onClear() else onSettings() }) {
        Icon(
            painter = icon,
            contentDescription = null
        )
    }
}