package com.mordva.personscreen.presentation.widget.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.model.movie.ShortMovie

@Composable
internal fun CategoriesHeader(
    groups: Map<String, List<ShortMovie>>,
    keys: List<String>,
    selected: Int,
    onClick: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(keys.size) { index ->
            val item = keys[index]
            val title = categoriesFilmography[item]
            val count = groups[item]?.size ?: 0

            if (title != null && count != 0) {
                FilterChip(
                    selected = selected == index,
                    onClick = { onClick(index) },
                    label = {
                        LabelContent(
                            title = title,
                            count = count.toString()
                        )
                    }
                )
            }
        }
    }
}

private val categoriesFilmography = mapOf(
    "actor" to "Актер",
    "producer" to "Продюсер",
    "cameo" to "Играет самого себя",
    "uncredited" to "Не указан в титрах"
)

@Composable
private fun LabelContent(
    title: String,
    count: String
) {
    Row {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = count
        )
    }
}