package com.mordva.search.presentation.widget.row

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun CategoryRow(
    index: Int,
    s: String,
    list: List<String> = listOf(),
    defaultDescription: String = "",
    onGenreClick: () -> Unit,
    onCountryClick: () -> Unit,
    onYearClick: () -> Unit
) {
    val description = if (list.isEmpty())
        defaultDescription
    else
        list.joinToString(", ")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                when (index) {
                    0 -> onGenreClick()
                    1 -> onCountryClick()
                    2 -> onYearClick()
                }
            }
            .padding(15.dp)

    ) {
        Text(
            text = s,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            text = description,
            textAlign = TextAlign.End,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}