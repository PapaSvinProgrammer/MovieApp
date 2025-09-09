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
fun BoxScope.SelectableSingleDropDownList(
    current: DropDownItem,
    list: List<DropDownItem>,
    onClick: (DropDownItem) -> Unit
) {
    val refactorList = remember(current) { list.weedOutList(current) }
    var isExpanded by remember { mutableStateOf(false) }
    val takeCount = remember(isExpanded) {
        if (isExpanded) refactorList.count() else 0
    }

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
                    item = current,
                    isSelect = true,
                    onClick = {
                        isExpanded = !isExpanded
                    }
                )
            }

            items(refactorList.take(takeCount)) { item ->
                SingleDropDownListItem(
                    item = item,
                    onClick = {
                        onClick(item)
                        isExpanded = !isExpanded
                    }
                )
            }
        }
    }
}

private fun List<DropDownItem>.weedOutList(current: DropDownItem): List<DropDownItem> {
    val res = mutableListOf<DropDownItem>()

    forEach { item ->
        if (item != current) {
            res.add(item)
        }
    }

    return res
}


