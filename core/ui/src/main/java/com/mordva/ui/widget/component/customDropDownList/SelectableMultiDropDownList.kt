package com.mordva.ui.widget.component.customDropDownList

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun BoxScope.SelectableMultiDropDownList(
    current: List<DropDownItem>,
    list: List<DropDownItem>,
    onClick: (DropDownItem) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val takeCount = remember(isExpanded) { if (isExpanded) list.count() else 0 }

    Box(
        modifier = Modifier
            .padding(15.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(18.dp)
            )
            .align(Alignment.TopEnd)
            .background(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.surfaceContainer
            )
            .animateContentSize()
    ) {
        LazyColumn {
            item {
                SingleDropDownListItem(
                    item = current.first(),
                    isSelect = true,
                    onClick = {
                        isExpanded = !isExpanded
                    }
                )
            }

            items(list.take(takeCount)) { item ->
                MultiDropDownListItem(
                    item = item,
                    isSelect = item in current,
                    onClick = { onClick(item) }
                )
            }
        }
    }
}
