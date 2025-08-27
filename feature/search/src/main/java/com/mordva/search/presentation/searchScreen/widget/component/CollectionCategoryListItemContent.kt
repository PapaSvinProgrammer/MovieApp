package com.mordva.search.presentation.searchScreen.widget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.ui.R
import com.mordva.navigation.CollectionListGraph
import com.mordva.search.presentation.searchScreen.util.collectionCategoryList
import com.mordva.ui.theme.Typography

internal fun LazyListScope.collectionCategoryListItemContent(
    navController: NavController
) {
    item {
        Text(
            modifier = Modifier.padding(15.dp),
            text = stringResource(R.string.categories),
            fontSize = Typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            collectionCategoryList.forEach {
                SuggestionChip(
                    label = {
                        Text(
                            text = it,
                            fontSize = Typography.bodySmall.fontSize
                        )
                    },
                    onClick = {
                        navController.navigate(
                            CollectionListGraph.CollectionListRoute(it)
                        )
                    }
                )
            }
        }
    }
}